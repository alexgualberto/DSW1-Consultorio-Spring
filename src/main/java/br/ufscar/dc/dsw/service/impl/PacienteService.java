package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IPacienteDAO;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.service.spec.IPacienteService;

@Service
@Transactional(readOnly = false)
public class PacienteService implements IPacienteService {

	@Autowired
	IPacienteDAO dao;
	
	public void salvar(Paciente paciente) {
		dao.save(paciente);
	}

	public void excluirPorCPF(String CPF) {
		dao.deleteByCPF(CPF);
	}

	@Transactional(readOnly = true)
	public Paciente buscarPorCPF(String CPF) {
		return dao.findByCPF(CPF);
	}

	@Transactional(readOnly = true)
	public List<Paciente> buscarTodos() {
		return dao.findAll();
	}
}
