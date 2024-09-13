package View;

import javax.swing.*;
import Controller.AdminController;
import Dto.DtoAdministrador;
import Model.Admin;

public class AdminView extends ViewPrototype { // Herdando de ViewPrototype
    private AdminController adminController;
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;

    public AdminView() {
        super("Cadastro do Administrador"); // Chamando o construtor da superclasse
        adminController = new AdminController();
        setSize(600,600);

        if (adminController.isAdminRegistered()) {
            JOptionPane.showMessageDialog(this, "Administrador já cadastrado. Redirecionando para a tela de login.");
            new AdminLoginView();
            this.dispose();
            return;
        }

        initComponents();
        setVisible(true);
    }

    @Override
    protected void initComponents() { // Implementando o m�todo abstrato
        nomeField = new JTextField();
        emailField = new JTextField();
        senhaField = new JPasswordField();

        addLabelAndField("Nome:", nomeField, 30, 30, 200, 25);
        addLabelAndField("Email:", emailField, 30, 70, 200, 25);
        addLabelAndField("Senha:", senhaField, 30, 110, 200, 25);

        JButton cadastrarButton = new JButton("Cadastrar Administrador");
        cadastrarButton.setBounds(100, 160, 200, 30);
        cadastrarButton.addActionListener(e -> cadastrarAdministrador());
        add(cadastrarButton);
    }

    private void cadastrarAdministrador() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());

        // Criar o objeto DTO com os dados do administrador
        DtoAdministrador dtoAdmin = new DtoAdministrador(nome, email, senha);
        
        // Chamar o controlador para criar o administrador
        Admin admin = adminController.createAdmin(dtoAdmin);

        if (admin != null) {
            JOptionPane.showMessageDialog(this, "Administrador cadastrado com sucesso!");
            new AdminLoginView();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Falha ao cadastrar administrador.");
        }
    }
}
