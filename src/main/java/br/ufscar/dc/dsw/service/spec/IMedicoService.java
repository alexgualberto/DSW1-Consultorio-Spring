package br.ufscar.dc.dsw.service.spec;
import java.util.List;
import br.ufscar.dc.dsw.domain.Medico;

public interface IMedicoService {

    Medico buscaPorId(String id);
    
    Medico buscarPorCRM(String CRM);

    void salvar(Medico medico);

    void excluirPorCRM(String CRM);

    List<Medico> buscarTodos();

    List<Medico> buscarTodosPorEspecialidade(String especialidade);

    List<String> buscarTodosEspecialidade();
}

