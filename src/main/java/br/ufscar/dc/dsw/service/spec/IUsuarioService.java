package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Usuario;

public interface IUsuarioService {

	Usuario buscarPorEmail(String email);

	void salvar(Usuario usuario);

	void excluirPorEmail(String email);
	
	Usuario buscarPorId(Long id);

	void excluirPorId(Long id);
}
