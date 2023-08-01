package br.ufscar.dc.dsw.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.service.spec.IConsultaService;
import br.ufscar.dc.dsw.service.spec.IPacienteService;
import br.ufscar.dc.dsw.service.spec.IMedicoService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {
	
	@Autowired
	private IConsultaService consultaService;

	@Autowired
	private IPacienteService pacienteService;

	@Autowired
	private IMedicoService medicoService;

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		String CPF = consultaService.buscaCFPPorEmail(email);

		if (CPF == null) {
			String CRM = consultaService.buscaCRMPorEmail(email);

			model.addAttribute("consultas", consultaService.buscarPorCRM(CRM));

			System.out.println("------------> " + CRM);
			System.out.println(consultaService.buscarPorCRM(CRM));
			
			return "consulta/lista";
		}
		else {
			model.addAttribute("consultas", consultaService.buscarPorCPF(CPF));

			System.out.println("------------> " + CPF);
			System.out.println(consultaService.buscarPorCPF(CPF));
			
			return "consulta/lista";
		}
	}

	@PostMapping("/listarPorEspecialidade")
    public String listarMedicosPorEspecialidade(@RequestParam(name = "especialidade", required = false) String especialidadeSelecionada, ModelMap model) {
        List<Medico> medicosFiltrados;

        if (especialidadeSelecionada != null && !especialidadeSelecionada.isEmpty()) {
            medicosFiltrados = medicoService.buscarTodosPorEspecialidade(especialidadeSelecionada);
        } else {
            medicosFiltrados = medicoService.buscarTodos();
        }

		model.addAttribute("especialidades", medicoService.buscarTodosEspecialidade());
        model.addAttribute("medicos", medicosFiltrados);
        return "consulta/listaMedicos";
    }

	
	@GetMapping("/cadastrar")
	public String listarMedicos(Consulta consulta, ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		String CPF = consultaService.buscaCFPPorEmail(email);
		model.addAttribute("consultas",consultaService.buscarPorCPF(CPF));


		model.addAttribute("especialidades", medicoService.buscarTodosEspecialidade());
		model.addAttribute("medicos", medicoService.buscarTodos());
		return "consulta/listaMedicos";
	}

	
    @PostMapping("/listarHorarios")
    public String listarHorarios(@RequestParam(name = "id_medico") String id_medico,
                                 @RequestParam(name = "data_consulta") String stringData_consulta,
                                 Consulta consulta, ModelMap model) {

        List<Time> intervalos = new ArrayList<>();
        Time inicioExpediente = Time.valueOf("08:00:00");
        Time fimExpediente = Time.valueOf("18:00:00");

        intervalos.add(inicioExpediente);

        Calendar cal = Calendar.getInstance();
        cal.setTime(inicioExpediente);
        while (cal.getTime().before(fimExpediente)) {
            cal.add(Calendar.MINUTE, 30);
            intervalos.add(new java.sql.Time(cal.getTimeInMillis()));
        }

        List<String> stringIntervalos = new ArrayList<String>();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        for (java.sql.Time time : intervalos) {
            stringIntervalos.add(sdf.format(time));
        }

        List<String> horariosFiltrados = new ArrayList<>();

        if (stringData_consulta != null && !stringData_consulta.isEmpty()) {
            try {
                SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

                Date data = new Date(sdfDate.parse(stringData_consulta).getTime());
                List<Time> horarios = consultaService.buscaPorData(data);

                List<String> stringHorarios = new ArrayList<>();
                for (Time horario : horarios) {
                    stringHorarios.add(sdfTime.format(horario));
                }

                Date currentDate = new Date(System.currentTimeMillis()); 

                Calendar currentCal = Calendar.getInstance();
                Time currentTime = new Time(currentCal.getTimeInMillis());

                Calendar calData = Calendar.getInstance();
                calData.setTime(data);

                Calendar calCurrentDate = Calendar.getInstance();
                calCurrentDate.setTime(currentDate);

                if (calData.get(Calendar.YEAR) == calCurrentDate.get(Calendar.YEAR) &&
                        calData.get(Calendar.MONTH) == calCurrentDate.get(Calendar.MONTH) &&
                        calData.get(Calendar.DAY_OF_MONTH) == calCurrentDate.get(Calendar.DAY_OF_MONTH)) {

                    horariosFiltrados = stringIntervalos.stream()
                            .filter(timeStr -> {
                                try {
                                    Time time = new Time(sdfTime.parse(timeStr).getTime());
                                    String currentTimeStr = sdfTime.format(currentTime);
                                    Time currentTimeOnly = new Time(sdfTime.parse(currentTimeStr).getTime());

                                    boolean isAfterCurrentTime = !time.before(currentTimeOnly);
                                    System.out.println(timeStr + " - isAfterCurrentTime: " + isAfterCurrentTime);
                                    return isAfterCurrentTime;
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                    return false; 
                                }
                            })
                            .collect(Collectors.toList());

                    System.out.println("Filtered time slots: " + horariosFiltrados);
                } else {
                    horariosFiltrados = stringIntervalos;
                    System.out.println("Selected date is not current date");
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        } else {
            horariosFiltrados = null;
            System.out.println("No date selected");
        }

        model.addAttribute("data_consulta", stringData_consulta);
        model.addAttribute("horarios", horariosFiltrados);
        model.addAttribute("id_medico", String.valueOf(id_medico));

        return "consulta/cadastro";
    }
	
	@GetMapping("/cadastrar/{id}")
	public String cadastrar(@PathVariable("id") Long id, Consulta consulta, ModelMap model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		String CPF = consultaService.buscaCFPPorEmail(email);
		model.addAttribute("paciente",pacienteService.buscarPorCPF(CPF));
		model.addAttribute("medico", usuarioService.buscarPorId(id));
		model.addAttribute("id_medico", String.valueOf(id));

		System.out.println("------------> " + CPF);

		return "consulta/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(@RequestParam(name="id_medico") String id, @RequestParam(name="data_consulta") String data, @RequestParam(name="horario") String hora, Consulta consulta, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "consulta/cadastro2";
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		String CPF = consultaService.buscaCFPPorEmail(email);

		Medico medico = medicoService.buscaPorId(id);
		String CRM = medico.getCRM();

		System.out.println(CPF);
		System.out.println(CRM);
		System.out.println(data);
		System.out.println(hora);

		consulta = new Consulta();
		consulta.setCPFpaciente(CPF);
		consulta.setCRMmedico(CRM);
		consulta.setStringData_consulta(data);
		consulta.setStringHora(hora);
		consultaService.salvar(consulta);
		
		attr.addFlashAttribute("sucess", "consulta.create.sucess");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
		return "redirect:/consultas/listar";
	}
}
