package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.service.spec.IPacienteService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {
	
	@Autowired
	private IPacienteService pacienteService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("pacientes",pacienteService.buscarTodos());
	 	return "paciente/lista";
	}

	
	@GetMapping("/cadastrar")
	public String cadastrar(Paciente paciente) {
		return "paciente/cadastro";
	}
	
	
	@PostMapping("/salvar")
	public String salvar(@Valid Paciente paciente, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "paciente/cadastro";
		}

        paciente.setPassword(encoder.encode(paciente.getPassword()));
		paciente.setRole("ROLE_PACIENTE");
		pacienteService.salvar(paciente);
		attr.addFlashAttribute("sucess", "paciente.create.sucess");
		return "redirect:/pacientes/listar";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Paciente paciente, BindingResult result, RedirectAttributes attr) {
		System.out.println(paciente.getPassword());
		paciente.setRole("ROLE_PACIENTE");
		pacienteService.salvar(paciente);
		attr.addFlashAttribute("sucess", "paciente.edit.sucess");
		return "redirect:/pacientes/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("paciente", usuarioService.buscarPorId(id));
		return "paciente/cadastro";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		usuarioService.excluirPorId(id);
		model.addAttribute("sucess", "paciente.delete.sucess");
		return "redirect:/pacientes/listar";
	}

}
