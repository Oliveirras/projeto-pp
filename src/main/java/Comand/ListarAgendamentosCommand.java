package Comand;

import java.util.List;

import javax.swing.JOptionPane;


import Controller.AgendamentoController;
import Dto.ServicoDTO;
import View.AgendamentoView;

public class ListarAgendamentosCommand implements Comando {
    private AgendamentoController agendamentoController;
    private AgendamentoView view;

    public ListarAgendamentosCommand(AgendamentoController agendamentoController, AgendamentoView view) {
        this.agendamentoController = agendamentoController;
        this.view = view;
    }

   
    public void executar() {
        List<ServicoDTO> servicos = agendamentoController.listarServicosAgendados();
        StringBuilder lista = new StringBuilder("Serviços Agendados:\n");
        for (ServicoDTO servico : servicos) {
            lista.append("Nome do Cliente: ").append(servico.getNomeCliente())
                 .append(", E-mail: ").append(servico.getEmail())
                 .append(", Tipo de Serviço: ").append(servico.getTipoServico())
                 .append(", Tipo: ").append(servico.isCaro() ? "Vip" : "Comum")
                 .append(", Ordem: ").append(servico.getOrdem())
                 .append(", Horário: ").append(servico.getHorario())
                 .append("\n");
        }
        JOptionPane.showMessageDialog(view, lista.toString());
    }
}
