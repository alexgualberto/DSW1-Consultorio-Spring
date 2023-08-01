package br.ufscar.dc.dsw.dao;

import java.util.List;
import java.sql.Date;
import java.sql.Time;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import br.ufscar.dc.dsw.domain.Consulta;

@SuppressWarnings("unchecked")
public interface IConsultaDAO extends CrudRepository<Consulta, Long>{
    
    Consulta findById(long id);
	
	List<Consulta> findAllByCPFpaciente(String CPF);

    List<Consulta> findAllByCRMmedico(String CRM);

	List<Consulta> findAll();
	
	Consulta save(Consulta consulta);

	void deleteById(Long id);

	@Query("SELECT u.CPF FROM Usuario u WHERE u.email = :email")
	String getCPFByEmail(@Param("email") String email);

	@Query("SELECT u.CRM FROM Usuario u WHERE u.email = :email")
	String getCRMByEmail(@Param("email") String email);

	@Query("SELECT c.hora FROM Consulta c WHERE c.data_consulta = :data")
	List<Time> getHoraByData_consulta(@Param("data") Date data);
}