package br.ufscar.dc.dsw.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.dao.IConsultaDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.service.spec.IConsultaService;

@Service
@Transactional(readOnly = false)
public class ConsultaService implements IConsultaService {

	@Autowired
	IConsultaDAO dao;

 
    @Transactional(readOnly = true)
    public List<Consulta> buscarPorCPF(String CPF) {
        return dao.findAllByCPFpaciente(CPF);
    }

    @Transactional(readOnly = true)
    public List<Consulta> buscarPorCRM(String CRM) {
        return dao.findAllByCRMmedico(CRM);
    }

    public void salvar(Consulta consulta) {
        dao.save(consulta);
    }

    public void excluirPorId(Long Id) {
        dao.deleteById(Id);
    }

    @Transactional(readOnly = true)
    public List<Consulta> buscarTodos() {
        return dao.findAll();
    }

    public String buscaCFPPorEmail(String email) {
        return dao.getCPFByEmail(email);
    }

    public String buscaCRMPorEmail(String email) {
        return dao.getCRMByEmail(email);
    }

    public List<Time> buscaPorData(Date data) {
        return dao.getHoraByData_consulta(data);
    }
}
