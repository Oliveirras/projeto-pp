package Comand;

import javax.swing.JOptionPane;

import Controller.AgendamentoController;
import Dto.ServicoDTO;
import View.AgendamentoView;

public class AgendarServicoCommand implements Comando {
    private AgendamentoController agendamentoController;
    private ServicoDTO servicoDTO;
    private AgendamentoView view;

    public AgendarServicoCommand(AgendamentoController agendamentoController, ServicoDTO servicoDTO, AgendamentoView view) {
        this.agendamentoController = agendamentoController;
        this.servicoDTO = servicoDTO;
        this.view = view;
    }

    @Override
    public void executar() {
        agendamentoController.agendarServico(servicoDTO);
        JOptionPane.showMessageDialog(view, "Serviço agendado com sucesso: " + servicoDTO.getTipoServico() + 
            " para " + servicoDTO.getNomeCliente() + " (" + servicoDTO.getEmail() + 
            "), Tipo: " + (servicoDTO.isCaro() ? "Caro" : "Barato") + 
            ", Ordem: " + servicoDTO.getOrdem() + ", Horário: " + servicoDTO.getHorario());

        view.atualizarHorariosDisponiveis(); // Atualiza a lista de horários disponíveis após o agendamento
    }
}
