package View;

import javax.swing.*;
import Strategy.PagamentoCartao;
import Strategy.PagamentoContext;
import Strategy.PagamentoPix;

import java.awt.*;
import java.util.regex.Pattern;

public class PagamentoView extends JFrame {
    private PagamentoContext pagamentoContext;

    public PagamentoView() {
        pagamentoContext = new PagamentoContext();
        initComponents();
    }

    private void initComponents() {
        setTitle("Forma de Pagamento");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel valorLabel = new JLabel("Valor: ");
        JTextField valorField = new JTextField(10);
        add(valorLabel);
        add(valorField);

        JButton pagamentoCartaoButton = new JButton("Pagar com Cartão");
        pagamentoCartaoButton.addActionListener(e -> {
            try {
                double valor = validarValor(valorField.getText());

                // Simulação de campos para pagamento com cartão
                String numeroCartao = JOptionPane.showInputDialog("Digite o número do cartão (16 dígitos):");
                validarNumeroCartao(numeroCartao);

                String nomeTitular = JOptionPane.showInputDialog("Digite o nome do titular do cartão:");
                validarNomeTitular(nomeTitular);

                String validade = JOptionPane.showInputDialog("Digite a validade do cartão (MM/AA):");
                validarValidadeCartao(validade);

                String cvv = JOptionPane.showInputDialog("Digite o código de segurança (CVV - 3 dígitos):");
                validarCVV(cvv);

                // Dados de cartão simulados após validação
                PagamentoCartao pagamentoCartao = new PagamentoCartao(numeroCartao, nomeTitular, validade, cvv);
                pagamentoContext.setEstrategiaPagamento(pagamentoCartao);
                pagamentoContext.processarPagamento(valor);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
        add(pagamentoCartaoButton);

        JButton pagamentoPixButton = new JButton("Pagar com Pix");
        pagamentoPixButton.addActionListener(e -> {
            try {
                double valor = validarValor(valorField.getText());

                // Simulação de chave Pix
                String chavePix = JOptionPane.showInputDialog("Digite a chave Pix:");
                validarChavePix(chavePix);

                PagamentoPix pagamentoPix = new PagamentoPix(chavePix);
                pagamentoContext.setEstrategiaPagamento(pagamentoPix);
                pagamentoContext.processarPagamento(valor);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
        add(pagamentoPixButton);

        // Botão Voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            dispose(); // Fecha a janela atual
            new ClienteOptionsView().setVisible(true); // Abre a janela ClienteOptionsView
        });
        add(voltarButton);
    }

    // Validações

    private double validarValor(String valorStr) {
        try {
            double valor = Double.parseDouble(valorStr);
            if (valor <= 0) {
                throw new IllegalArgumentException("O valor deve ser maior que zero.");
            }
            return valor;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("O valor inserido é inválido.");
        }
    }

    private void validarNumeroCartao(String numeroCartao) {
        if (!numeroCartao.matches("\\d{16}")) {
            throw new IllegalArgumentException("Número do cartão inválido. Deve conter 16 dígitos.");
        }
    }

    private void validarNomeTitular(String nomeTitular) {
        if (!nomeTitular.matches("[A-Za-z ]+")) {
            throw new IllegalArgumentException("Nome do titular inválido. Deve conter apenas letras e espaços.");
        }
    }

    private void validarValidadeCartao(String validade) {
        if (!validade.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            throw new IllegalArgumentException("Validade do cartão inválida. Deve estar no formato MM/AA.");
        }
    }

    private void validarCVV(String cvv) {
        if (!cvv.matches("\\d{3}")) {
            throw new IllegalArgumentException("CVV inválido. Deve conter 3 dígitos.");
        }
    }

    private void validarChavePix(String chavePix) {
        if (!validarEmail(chavePix) && !validarCPF(chavePix) && !validarChaveAleatoria(chavePix)) {
            throw new IllegalArgumentException("Chave Pix inválida. Deve ser um e-mail, CPF ou chave aleatória.");
        }
    }

    private boolean validarEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    private boolean validarCPF(String cpf) {
        return cpf.matches("\\d{11}");
    }

    private boolean validarChaveAleatoria(String chave) {
        return chave.matches("[A-Za-z0-9]+");
    }
}
