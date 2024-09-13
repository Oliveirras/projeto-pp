package View;

import Controller.ClienteController;
import Dto.DtoCliente;

import javax.swing.*;
import java.awt.*;

public class ClienteView extends ViewPrototype {
    private ClienteController clienteController;
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField telefoneField;
    private JTextField enderecoField;
    private JPasswordField senhaField;

    public ClienteView() {
        super("Gerenciamento de Clientes");
        clienteController = new ClienteController();
        setSize(600, 600);
        this.setLocationRelativeTo(null);
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon fundo = new ImageIcon("C:\\Users\\Glêisson\\Desktop\\ProjoetoManicure\\ProjetoManicureEpedicure\\src\\main\\java\\View\\Imagens\\PlanoDeFundo9.png");
                g.drawImage(fundo.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        panel.setLayout(null);
        setContentPane(panel);
        initComponents();
        setVisible(true);
    }

    @Override
    protected void initComponents() {
        nomeField = new JTextField();
        emailField = new JTextField();
        telefoneField = new JTextField();
        enderecoField = new JTextField();
        senhaField = new JPasswordField();

        addLabelAndField("Nome:", nomeField, 50, 120, 300, 25);
        addLabelAndField("Email:", emailField, 50, 150, 300, 25);
        addLabelAndField("Telefone:", telefoneField, 50, 180, 300, 25);
        addLabelAndField("Endereço:", enderecoField, 50, 210, 300, 25);
        addLabelAndField("Senha:", senhaField, 50, 240, 300, 25);
  
        JButton cadastrarButton = new JButton("Cadastrar Cliente");
        cadastrarButton.setBounds(140, 270, 140, 30);
        cadastrarButton.addActionListener(e -> {
            String nome = nomeField.getText().trim();
            String email = emailField.getText().trim();
            String telefone = telefoneField.getText().trim();
            String endereco = enderecoField.getText().trim();
            String senha = new String(senhaField.getPassword()).trim();

            if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || endereco.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
                return;
            }

            String resultado = clienteController.cadastrarCliente(nome, email, telefone, endereco, senha);
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso. ID: " + resultado);
        });
        add(cadastrarButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(300, 270, 140, 30);
        loginButton.addActionListener(e -> {
            new ClienteLoginView().setVisible(true);
            dispose();
        });
        add(loginButton);
        

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(140, 310, 140, 30);
        voltar.addActionListener(e -> {
            new InitialView().setVisible(true);
            dispose();
        });
        add(voltar);

        titulo();
        adicionarImagem();
    }

    public void titulo() {
        JLabel lbTitulo = new JLabel("Studio Linda");
        lbTitulo.setBounds(150, 10, 350, 100);
        lbTitulo.setFont(new Font("Times New Roman", Font.PLAIN, 60));
        lbTitulo.setForeground(Color.DARK_GRAY);
        add(lbTitulo);
    }

    public void adicionarImagem() {
        ImageIcon icone = new ImageIcon("C:\\Users\\Glêisson\\Desktop\\ProjoetoManicure\\ProjetoManicureEpedicure\\src\\main\\java\\View\\Imagens\\ImagemGereC.png");
        JLabel lbImagem = new JLabel(icone, 0);
        this.add(lbImagem);
        lbImagem.setBounds(400, 150, 150, 230);
        lbImagem.setOpaque(true);
    }
}
