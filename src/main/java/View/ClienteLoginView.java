package View;

import Controller.ClienteController;
import javax.swing.*;
import java.awt.*;

public class ClienteLoginView extends ViewPrototype {
    private JTextField emailField;
    private JPasswordField senhaField;
    private ClienteController clienteController;

    public ClienteLoginView() {
        super("Login do Cliente");
        clienteController = new ClienteController();
        setSize(600,600);
        this.setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    public void adicionarTitulo() {
        JLabel lbTitulo = new JLabel("Studio Linda");
        lbTitulo.setBounds(120, 40, 390, 150);
        lbTitulo.setFont(new Font("Times new roman", Font.PLAIN, 70));
        lbTitulo.setForeground(Color.DARK_GRAY);
        add(lbTitulo);
    }

    public void adicionarImagem() {
        ImageIcon icone = new ImageIcon("C:\\Users\\Glêisson\\Desktop\\ProjoetoManicure\\ProjetoManicureEpedicure\\src\\main\\java\\View\\Imagens\\ImagemLoginCliente165.png");
        JLabel lbImagem = new JLabel(icone, 0);
        this.add(lbImagem);
        lbImagem.setBounds(190, 150, 260, 165);
        lbImagem.setOpaque(true);
    }

    @Override
    protected void initComponents() {
        emailField = new JTextField();
        senhaField = new JPasswordField();

        addLabelAndField("Email:", emailField, 50, 330, 210, 25);
        addLabelAndField("Senha:", senhaField, 50, 370, 210, 25);

        // Bot�o de Login
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(200, 410, 150, 30);
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String senha = new String(senhaField.getPassword());
            if (clienteController.authenticateCliente(email, senha)) {
                new ClienteOptionsView().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Email ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Bot�o de Voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(200, 450, 150, 30);
        voltarButton.addActionListener(e -> {
        	  new ClienteView().setVisible(true);
            dispose();
        });

        add(loginButton);
        add(voltarButton);
        adicionarTitulo();
        adicionarImagem();
    }

    public void adicionarImagemFundo() {
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon fundo = new ImageIcon("C:\\Users\\Glêisson\\Desktop\\ProjoetoManicure\\ProjetoManicureEpedicure\\src\\main\\java\\View\\Imagens\\PlanoDeFundo8.png");
                g.drawImage(fundo.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        panel.setLayout(null);
        setContentPane(panel);
    }
}
