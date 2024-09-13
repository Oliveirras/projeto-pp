package Strategy;

public class PagamentoCartao implements PagamentoStrategy {
    private String numeroCartao;
    private String nomeTitular;
    private String dataValidade;
    private String codigoSeguranca;

    public PagamentoCartao(String numeroCartao, String nomeTitular, String dataValidade, String codigoSeguranca) {
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.dataValidade = dataValidade;
        this.codigoSeguranca = codigoSeguranca;
    }

    @Override
    public void pagar(double valor) {
        // Lógica de processamento de pagamento via cartão
        System.out.println("Pagamento de R$" + valor + " realizado com cartão de crédito.");
    }
    
    /*
     * Aqui está um exemplo de como os campos devem ser preenchidos no sistema de pagamento:

Para pagamento com Cartão de Crédito:
Valor: Informe o valor total que será pago (ex: 100.00).

Número do Cartão:

Exemplo: 1234 5678 9012 3456
(16 dígitos sem letras ou símbolos adicionais)
Nome do Titular:

Exemplo: João Silva
(Nome completo do titular do cartão, exatamente como está no cartão)
Validade (MM/AA):

Exemplo: 12/25
(Dois dígitos para o mês e dois dígitos para o ano)
Código de Segurança:

Exemplo: 123
(O código de 3 dígitos geralmente encontrado na parte de trás do cartão)
Para pagamento com Pix:
Valor: Informe o valor total que será pago (ex: 100.00).

Chave Pix:

Exemplo: joao.silva@email.com
Pode ser um e-mail, CPF, telefone, ou chave aleatória associada à conta do Pix.
Exemplo Completo:
Pagamento com Cartão:
Valor: 200.00
Número do Cartão: 1234 5678 9012 3456
Nome do Titular: Maria Oliveira
Validade: 08/26
Código de Segurança: 456
Pagamento via Pix:
Valor: 150.00
Chave Pix: maria.oliveira@pix.com
     */
}
