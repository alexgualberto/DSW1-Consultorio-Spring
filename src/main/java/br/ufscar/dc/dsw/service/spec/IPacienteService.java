package br.ufscar.dc.dsw.service.spec;


import br.ufscar.dc.dsw.domain.Paciente;
import java.util.List;

public interface IPacienteService {

    Paciente buscarPorCPF(String CPF);

    void salvar(Paciente paciente);

    void excluirPorCPF(String CPF);

    List<Paciente> buscarTodos();
}
