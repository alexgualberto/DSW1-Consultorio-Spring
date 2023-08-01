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
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.service.spec.IMedicoService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@Controller
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private IMedicoService medicoService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder encoder;
	


	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("medicos",medicoService.buscarTodos());
	 	return "medico/lista";
	}

	
	@GetMapping("/cadastrar")
	public String cadastrar(Medico medico) {
		return "medico/cadastro";
	}
	
	
	@PostMapping("/salvar")
	public String salvar(@Valid Medico medico, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "medico/cadastro";
		}

        medico.setPassword(encoder.encode(medico.getPassword()));
		medico.setRole("ROLE_MEDICO");
		medicoService.salvar(medico);
		attr.addFlashAttribute("sucess", "medico.create.sucess");
		return "redirect:/medicos/listar";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Medico medico, BindingResult result, RedirectAttributes attr) {
		System.out.println(medico.getPassword());
		medico.setRole("ROLE_MEDICO");
		medicoService.salvar(medico);
		attr.addFlashAttribute("sucess", "medico.edit.sucess");
		return "redirect:/medicos/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("medico", usuarioService.buscarPorId(id));
		return "medico/cadastro";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		usuarioService.excluirPorId(id);
		model.addAttribute("sucess", "medico.delete.sucess");
		return "redirect:/medicos/listar";
	}

}


	
