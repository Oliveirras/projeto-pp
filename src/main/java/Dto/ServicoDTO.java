package Dto;

public class ServicoDTO {
    private String tipoServico;
    private boolean caro;
    private String nomeCliente;
    private String email;
    private int ordem;
    private String horario; // Novo campo para horário

    public ServicoDTO(String tipoServico, boolean caro, String nomeCliente, String email, int ordem, String horario) {
        this.tipoServico = tipoServico;
        this.caro = caro;
        this.nomeCliente = nomeCliente;
        this.email = email;
        this.ordem = ordem;
        this.horario = horario; // Inicializa o novo campo
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public boolean isCaro() {
        return caro;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getEmail() {
        return email;
    }

    public int getOrdem() {
        return ordem;
    }

    public String getHorario() {
        return horario;
    }
}
