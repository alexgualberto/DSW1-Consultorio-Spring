package br.ufscar.dc.dsw.service.spec;

import java.util.List;
import java.sql.Date;
import java.sql.Time;


import br.ufscar.dc.dsw.domain.Consulta;

public interface IConsultaService {

	

    List<Consulta> buscarPorCPF(String CPF);

    List<Consulta> buscarPorCRM(String CRM);

    void salvar(Consulta consulta);

    void excluirPorId(Long Id);

    List<Consulta> buscarTodos();

    String buscaCFPPorEmail(String email);

    String buscaCRMPorEmail(String email);

    List<Time> buscaPorData(Date data);
}
