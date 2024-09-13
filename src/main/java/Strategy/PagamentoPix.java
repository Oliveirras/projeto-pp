package Strategy;

public class PagamentoPix implements PagamentoStrategy {
    private String chavePix;

    public PagamentoPix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public void pagar(double valor) {
        // Lógica de processamento de pagamento via Pix
        System.out.println("Pagamento de R$" + valor + " realizado via Pix.");
    }
}
