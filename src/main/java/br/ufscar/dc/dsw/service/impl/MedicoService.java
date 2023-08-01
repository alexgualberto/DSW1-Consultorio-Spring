package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IMedicoDAO;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.service.spec.IMedicoService;

@Service
@Transactional(readOnly = false)
public class MedicoService implements IMedicoService {

	@Autowired
	IMedicoDAO dao;

	public Medico buscaPorId(String id) {
		return dao.findById(Long.parseLong(id));
	}
	
	public void salvar(Medico medico) {
		dao.save(medico);
	}

	public void excluirPorCRM(String CRM) {
		dao.deleteByCRM(CRM);
	}

	@Transactional(readOnly = true)
	public Medico buscarPorCRM(String CRM) {
		return dao.findByCRM(CRM);
	}

	@Transactional(readOnly = true)
	public List<Medico> buscarTodos() {
		return dao.findAll();
	}

    @Transactional(readOnly = true)
	public List<Medico> buscarTodosPorEspecialidade(String especialidade) {
		return dao.findAllByEspecialidade(especialidade);
	}

    @Transactional(readOnly = true)
	public List<String> buscarTodosEspecialidade() {
		return dao.findAllEspecialidade();
	}


}
