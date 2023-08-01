package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Medico;

@SuppressWarnings("unchecked")
public interface IMedicoDAO extends CrudRepository<Medico, Long>{

    Medico findById(long id);

    Medico findByCRM(String CRM);

	List<Medico> findAll();

    List<Medico> findAllByEspecialidade(String especialidade);
	
	Medico save(Medico medico);

	void deleteByCRM(String CRM);

    @Query("SELECT DISTINCT m.especialidade FROM Medico m")
    List<String> findAllEspecialidade();
}
