package Controller;

import Dao.ServicoDAO;
import Dto.ServicoDTO;
import Validacoes.CommonValidator;

import java.util.List;

public class AgendamentoController {
    private ServicoDAO servicoDAO;

    public AgendamentoController() {
        servicoDAO = new ServicoDAO();
    }

    // Agenda um serviço, adicionando-o ao banco de dados
    public void agendarServico(ServicoDTO servicoDTO) {
        // Valida se o nome do serviço e o nome do cliente são válidos
        if (!CommonValidator.validarNomeServico(servicoDTO) || !CommonValidator.validarNomeCliente(servicoDTO)) {
            throw new IllegalArgumentException("Nome de serviço ou cliente inválido.");
        }

        servicoDAO.adicionarServico(servicoDTO);
    }

    // Lista os serviços agendados
    public List<ServicoDTO> listarServicosAgendados() {
        return servicoDAO.listarServicos();
    }

    // Retorna a próxima ordem para o serviço
    public int getProximaOrdem() {
        return servicoDAO.getNextOrder();
    }

    // Lista os horários disponíveis
    public List<String> listarHorariosDisponiveis() {
        return servicoDAO.getHorariosDisponiveis();
    }
}
