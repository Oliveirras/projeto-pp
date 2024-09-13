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
        // L�gica de processamento de pagamento via cart�o
        System.out.println("Pagamento de R$" + valor + " realizado com cart�o de cr�dito.");
    }
    
    /*
     * Aqui est� um exemplo de como os campos devem ser preenchidos no sistema de pagamento:

Para pagamento com Cart�o de Cr�dito:
Valor: Informe o valor total que ser� pago (ex: 100.00).

N�mero do Cart�o:

Exemplo: 1234 5678 9012 3456
(16 d�gitos sem letras ou s�mbolos adicionais)
Nome do Titular:

Exemplo: Jo�o Silva
(Nome completo do titular do cart�o, exatamente como est� no cart�o)
Validade (MM/AA):

Exemplo: 12/25
(Dois d�gitos para o m�s e dois d�gitos para o ano)
C�digo de Seguran�a:

Exemplo: 123
(O c�digo de 3 d�gitos geralmente encontrado na parte de tr�s do cart�o)
Para pagamento com Pix:
Valor: Informe o valor total que ser� pago (ex: 100.00).

Chave Pix:

Exemplo: joao.silva@email.com
Pode ser um e-mail, CPF, telefone, ou chave aleat�ria associada � conta do Pix.
Exemplo Completo:
Pagamento com Cart�o:
Valor: 200.00
N�mero do Cart�o: 1234 5678 9012 3456
Nome do Titular: Maria Oliveira
Validade: 08/26
C�digo de Seguran�a: 456
Pagamento via Pix:
Valor: 150.00
Chave Pix: maria.oliveira@pix.com
     */
}
