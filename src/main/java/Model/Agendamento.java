package Model;

import Dto.DtoCliente;
import Dto.ServicoDTO;

public class Agendamento {
    private DtoCliente cliente;
    private ServicoDTO servico;
    private String hora; // Alterado de LocalDateTime para String

    public Agendamento(DtoCliente cliente, ServicoDTO servico, String hora) {
        this.cliente = cliente;
        this.servico = servico;
        this.hora = hora;
    }

    // Getters e Setters
    public DtoCliente getCliente() {
        return cliente;
    }

    public void setCliente(DtoCliente cliente) {
        this.cliente = cliente;
    }

    public ServicoDTO getServico() {
        return servico;
    }

    public void setServico(ServicoDTO servico) {
        this.servico = servico;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "cliente=" + cliente +
                ", servico=" + servico +
                ", hora=" + hora +
                '}';
    }
}
