package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Validacoes.CommonValidator;

import Controller.AdminController;

public class InitialView extends ViewPrototype { // Herdando de ViewPrototype
    private JButton clienteButton;
    private JButton adminButton;
    private AdminController adminController;

    public InitialView() {
        super("Tela inicial"); // Chamando o construtor da superclasse
        this.setSize(600,600);
        adminController = new AdminController();
        this.setLocationRelativeTo((Component)null);
        setResizable(false);
        initComponents();
        setVisible(true);
        //getContentPane().setBackground(new Color(255, 240, 245)); // Rosa claro

    }

    public void adicionarTitulo(){
        JLabel lbTitulo = new JLabel("Studio Linda");
        lbTitulo.setBounds(120,60,390,150);
        lbTitulo.setFont(new Font("Times new roman", Font.PLAIN,70));
        lbTitulo.setForeground(Color.DARK_GRAY);
        add(lbTitulo);
    }
    public void adicionarImagem(){
        ImageIcon icone = new ImageIcon("C:\\Users\\Glêisson\\Desktop\\ProjoetoManicure\\ProjetoManicureEpedicure\\src\\main\\java\\View\\Imagens\\imagemTelaInicial.png");
        JLabel lbImagem = new JLabel(icone, JLabel.CENTER);
        add(lbImagem);
        lbImagem.setBounds(330,200,210,150);
        lbImagem.setOpaque(true);
    }

    @Override
    protected void initComponents() { // Implementando o metodo abstrato
        clienteButton = new JButton("Clientes");
        clienteButton.setBounds(120, 320, 200, 30);
        clienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClienteView().setVisible(true);
                dispose();
            }
        });
        add(clienteButton);

        adminButton = new JButton("Administrador");
        adminButton.setBounds(120, 280, 200, 30);
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (adminController.isAdminRegistered()) {
                    new AdminLoginView().setVisible(true);
                    dispose();
                } else {
                    new AdminRegistrationView().setVisible(true);
                    dispose();
                }
            }
        });
        add(adminButton);
        adicionarTitulo();
        adicionarImagem();
    }
}
