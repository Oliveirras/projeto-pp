package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.ServicoController;
import Dto.ServicosDisponiveisDto;

public class ServicoView extends JFrame {
    private ServicoController servicoControlador;
    private JTextField nomeField;
    private JTextField custoField;
    private JTextField tipoField;
    private JButton salvarButton;
    private JButton voltarButton;

    public ServicoView() {
        servicoControlador = new ServicoController();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Cadastro de Serviços");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margem ao redor

        // Painel para os campos de texto e labels
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento interno
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Ocupa toda a linha

        // Componentes
        JLabel nomeLabel = new JLabel("   Nome do Serviço:");
        nomeField = new JTextField(20);
        JLabel custoLabel = new JLabel("  Custo do Serviço:");
        custoField = new JTextField(20);
        JLabel tipoLabel = new JLabel("  Tipo de Serviço (vip ou comum):");
        tipoField = new JTextField(20);

        salvarButton = new JButton("Salvar");
        salvarButton.setBackground(new Color(60, 179, 113)); // Verde suave
        salvarButton.setForeground(Color.WHITE);
        salvarButton.setFocusPainted(false);
        salvarButton.setFont(new Font("Arial", Font.BOLD, 14));

        voltarButton = new JButton("Voltar");
        voltarButton.setBackground(new Color(220, 20, 60)); // Vermelho suave
        voltarButton.setForeground(Color.WHITE);
        voltarButton.setFocusPainted(false);
        voltarButton.setFont(new Font("Arial", Font.BOLD, 14));

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarServico();
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                new AdminOptionsView().setVisible(true); // Abre a janela AdminOptionsView
            }
        });

        // Adicionar componentes ao painel de formulário
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nomeLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(custoLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(custoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(tipoLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(tipoField, gbc);

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(salvarButton);
        buttonPanel.add(voltarButton);

        // Adicionar os painéis ao painel principal
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adicionar o painel principal ao JFrame
        add(mainPanel);
    }

    private void salvarServico() {
        String nome = nomeField.getText();
        double custo;
        String tipo = tipoField.getText();

        try {
            custo = Double.parseDouble(custoField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O custo deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!tipo.equalsIgnoreCase("vip") && !tipo.equalsIgnoreCase("comum")) {
            JOptionPane.showMessageDialog(this, "Tipo de serviço inválido. Use 'vip' ou 'comum'.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ServicosDisponiveisDto servicoDTO = new ServicosDisponiveisDto(nome, custo, tipo);
        servicoControlador.adicionarServico(servicoDTO, tipo);

        // Limpar campos após o salvamento
        nomeField.setText("");
        custoField.setText("");
        tipoField.setText("");

        JOptionPane.showMessageDialog(this, "Serviço cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
