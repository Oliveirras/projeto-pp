package Strategy;

public class PagamentoContext {
    private PagamentoStrategy estrategiaPagamento;

    public void setEstrategiaPagamento(PagamentoStrategy estrategiaPagamento) {
        this.estrategiaPagamento = estrategiaPagamento;
    }

    public void processarPagamento(double valor) {
        if (estrategiaPagamento != null) {
            estrategiaPagamento.pagar(valor);
        } else {
            System.out.println("Nenhuma estratégia de pagamento selecionada.");
        }
    }
}
