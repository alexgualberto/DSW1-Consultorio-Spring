package br.ufscar.dc.dsw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IMedicoDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.dao.IPacienteDAO;
import br.ufscar.dc.dsw.dao.IConsultaDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Consulta;


@SpringBootApplication
public class ConsultorioMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultorioMvcApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner demo(IMedicoDAO medicoDAO, IUsuarioDAO usuarioDAO, IPacienteDAO pacienteDAO, IConsultaDAO consultaDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {
			
			Medico m1 = new Medico();
			m1.setEmail("alexandre.santos.gualberto@gmail.com");
			m1.setPassword(encoder.encode("123"));
			m1.setRole("ROLE_MEDICO");
			m1.setName("Alexandre");
			m1.setCRM("SP123456");
			m1.setEspecialidade("Cardiologista");
			m1.setEnabled(true);
			medicoDAO.save(m1);

			Medico m2 = new Medico();
			m2.setEmail("eduardo.santos.gualberto@gmail.com");
			m2.setPassword(encoder.encode("123"));
			m2.setRole("ROLE_MEDICO");
			m2.setName("Eduardo");
			m2.setCRM("SP123457");
			m2.setEspecialidade("Endocrinologista");
			m2.setEnabled(true);
			medicoDAO.save(m2);

			Usuario u1 = new Usuario();
			u1.setEmail("admin");
			u1.setPassword(encoder.encode("admin"));
			u1.setName("Administrador");
			u1.setRole("ROLE_ADMIN");
			u1.setEnabled(true);
			usuarioDAO.save(u1);

			Usuario u2 = new Usuario();
			u2.setEmail("admin2");
			u2.setPassword(encoder.encode("admin"));
			u2.setName("Administrador");
			u2.setRole("ROLE_ADMIN");
			u2.setEnabled(true);
			usuarioDAO.save(u2);

			Paciente p1 = new Paciente();
			p1.setEmail("paciente1@example.com");
			p1.setPassword(encoder.encode("123"));
			p1.setRole("ROLE_PACIENTE");
			p1.setName("Paciente 1");
			p1.setCPF("12345678901");
			p1.setTelefone("99999999999");
			p1.setSexo("M");
			// Defina a data de nascimento utilizando o método setStringData_Nascimento
			System.out.println("pre data");
			p1.setStringData_Nascimento("2000-01-01");
			System.out.println("pos data");
			p1.setEnabled(true);
			System.out.println("pre save");
			pacienteDAO.save(p1);
			System.out.println("pos save");

			Consulta c1 = new Consulta();
			c1.setCPFpaciente(p1.getCPF());
			c1.setCRMmedico(m1.getCRM());
			c1.setStringData_consulta("2023-12-12");
			c1.setStringHora("12:00");
			consultaDAO.save(c1);

			Consulta c2 = new Consulta();
			c2.setCPFpaciente(p1.getCPF());
			c2.setCRMmedico(m2.getCRM());
			c2.setStringData_consulta("2023-11-11");
			c2.setStringHora("17:00");
			consultaDAO.save(c2);
		};
	}
	
}