package br.ufscar.dc.dsw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@Service
@Transactional(readOnly = false)
public class UsuarioService implements IUsuarioService {

	@Autowired
	IUsuarioDAO dao;

	public void salvar(Usuario usuario) {
		dao.save(usuario);
	}

	public void excluirPorEmail(String email) {
		dao.deleteByEmail(email);
	}

	public void excluirPorId(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorEmail(String email) {
		return dao.findByEmail(email);
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

}
