package View;

import Controller.AdminController;



import Model.Admin;

import javax.swing.*;
import Validacoes.CommonValidator;

public class AdminRegistrationView extends ViewPrototype {
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private AdminController adminController;

    public AdminRegistrationView() {
        super("Cadastro de Administrador");
        adminController = new AdminController();
        setSize(600,600);
        initComponents();
        setVisible(true);
    }

    @Override
    protected void initComponents() {
        nomeField = new JTextField();
        emailField = new JTextField();
        senhaField = new JPasswordField();
        
        addLabelAndField("Nome:", nomeField, 30, 30, 150, 25);
        addLabelAndField("Email:", emailField, 30, 70, 150, 25);
        addLabelAndField("Senha:", senhaField, 30, 110, 150, 25);

        JButton registerButton = new JButton("Cadastrar");
        registerButton.setBounds(100, 150, 150, 30);
        registerButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String email = emailField.getText();
            String senha = new String(senhaField.getPassword());

            if (!CommonValidator.validarNome(nome)) {
                JOptionPane.showMessageDialog(this, "O nome deve ter pelo menos 3 caracteres e não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (!CommonValidator.validarEmail(email)) {
                JOptionPane.showMessageDialog(this, "O email deve estar no formato correto (exemplo@dominio.com).", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (!CommonValidator.validarSenha(senha)) {
                JOptionPane.showMessageDialog(this, "A senha deve ter pelo menos 6 caracteres e incluir letras e números.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                Admin admin = new Admin(nome, email, senha);
                if (adminController.registerAdmin(admin)) { // Assumindo que registerAdmin retorna um boolean
                    JOptionPane.showMessageDialog(this, "Administrador cadastrado com sucesso.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao cadastrar administrador. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(registerButton);
    }

}
