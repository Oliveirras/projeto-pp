package Model;

public class Servico {
    private String tipoServico;
    private Tipo tipo;
    private String nomeCliente; // Campo para o nome do cliente
    private String email; // Novo campo para e-mail

    public enum Tipo {
        COMUM, VIP
    }

    private Servico(Builder builder) {
        this.tipoServico = builder.tipoServico;
        this.tipo = builder.tipo;
        this.nomeCliente = builder.nomeCliente; // Inicialização do nome do cliente
        this.email = builder.email; // Inicialização do e-mail
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getEmail() {
        return email; // Getter para o e-mail
    }

    public static class Builder {
        private String tipoServico;
        private Tipo tipo;
        private String nomeCliente; // Campo para o nome do cliente
        private String email; // Novo campo para e-mail

        public Builder tipoServico(String tipoServico) {
            this.tipoServico = tipoServico;
            return this;
        }

        public Builder tipo(Tipo tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder nomeCliente(String nomeCliente) {
            this.nomeCliente = nomeCliente;
            return this;
        }

        public Builder email(String email) {
            this.email = email; // Configura o e-mail
            return this;
        }

        public Servico build() {
            return new Servico(this);
        }
    }
}
