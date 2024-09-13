package View;

import javax.swing.*;
import Controller.AdminController;
import java.awt.*;

public class AdminLoginView extends ViewPrototype {
    private JTextField emailField;
    private JPasswordField senhaField;
    private AdminController adminController;

    public AdminLoginView() {
        super("Login do Administrador");
        adminController = new AdminController();
        setSize(600, 600);
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true);
    }

    public void adicionarTitulo() {
        JLabel lbtitulo = new JLabel("Studio Linda");
        lbtitulo.setBounds(200, 10, 300, 100);
        lbtitulo.setFont(new Font("Times new roman", Font.PLAIN, 50));
        lbtitulo.setForeground(Color.DARK_GRAY);
        add(lbtitulo);
    }

    private void adicionarImagem() {
        ImageIcon icone = new ImageIcon("C:\\Users\\Glêisson\\Desktop\\ProjoetoManicure\\ProjetoManicureEpedicure\\src\\main\\java\\View\\Imagens\\ADMLogin.png");
        JLabel lbImagem = new JLabel(icone, 0);
        this.add(lbImagem);
        lbImagem.setBounds(90, 200, 200, 300);
        lbImagem.setOpaque(true);
    }

    @Override
    protected void initComponents() {
        emailField = new JTextField();
        senhaField = new JPasswordField();

        // Usando o método utilitário da ViewPrototype para adicionar os campos com labels
        addLabelAndField("Email:", emailField, 200, 140, 150, 30);
        addLabelAndField("Senha:", senhaField, 200, 180, 150, 30);

        // Botão de Login
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(290, 220, 100, 30);
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String senha = new String(senhaField.getPassword());
            if (email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email e senha são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (adminController.authenticateAdmin(email, senha)) {
                new AdminOptionsView().setVisible(true);
                dispose(); // Fecha a janela de login
            } else {
                JOptionPane.showMessageDialog(this, "Email ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(loginButton);

        // Botão Voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(290, 260, 100, 30); // Ajuste a posição conforme necessário
        voltarButton.addActionListener(e -> {
            dispose(); // Fecha a janela atual de login
            new InitialView().setVisible(true); // Presumindo que MainView seja a tela anterior
        });
        add(voltarButton);

        adicionarTitulo();
        adicionarImagem();
    }
}
