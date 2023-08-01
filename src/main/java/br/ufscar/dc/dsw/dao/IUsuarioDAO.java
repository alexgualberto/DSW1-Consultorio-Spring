package br.ufscar.dc.dsw.dao;


import org.springframework.data.repository.CrudRepository;


import br.ufscar.dc.dsw.domain.Usuario;

@SuppressWarnings("unchecked")
public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {
	
	Usuario findByEmail(String email);
	
	Usuario save(Usuario usuario);

	Usuario findById(long id);

	void deleteById(Long id);

	void deleteByEmail(String email);
}