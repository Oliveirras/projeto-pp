package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Email.EmailSender;
import Email.Observer;

public class EmailApp extends ViewPrototype implements Observer {
    private JTextField remetenteField;
    private JPasswordField senhaField;
    private JTextField destinatarioField;
    private JTextField assuntoField;
    private JTextArea msgField;
    private JLabel statusLabel;
    private EmailSender emailSender;

    public EmailApp() {
        super("Email Sender");
        emailSender = new EmailSender();
        emailSender.addObserver(this);
        setSize(400, 400);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    @Override
    protected void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Campos de entrada
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Remetente:"), gbc);
        gbc.gridx = 1;
        remetenteField = new JTextField(20);
        inputPanel.add(remetenteField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Senha:"), gbc);
        gbc.gridx = 1;
        senhaField = new JPasswordField(20);
        inputPanel.add(senhaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Destinatário:"), gbc);
        gbc.gridx = 1;
        destinatarioField = new JTextField(20);
        inputPanel.add(destinatarioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Assunto:"), gbc);
        gbc.gridx = 1;
        assuntoField = new JTextField(20);
        inputPanel.add(assuntoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Mensagem:"), gbc);
        gbc.gridx = 1;
        msgField = new JTextArea(5, 20);
        msgField.setLineWrap(true);
        msgField.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(msgField);
        inputPanel.add(scrollPane, gbc);

        // Botão de envio
        JPanel buttonPanel = new JPanel();
        JButton sendButton = new JButton("Enviar");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String remetente = remetenteField.getText();
                String senha = new String(senhaField.getPassword());
                String destinatario = destinatarioField.getText();
                String assunto = assuntoField.getText();
                String msg = msgField.getText();
                emailSender.enviarEmail(senha, remetente, destinatario, msg, assunto);
            }
        });
        buttonPanel.add(sendButton);

        // Status
        statusLabel = new JLabel("Status: ");
        buttonPanel.add(statusLabel);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void update(String message) {
        statusLabel.setText("Status: " + message);
    }

    public void preencherCamposEmail(String destinatario, String assunto, String mensagem) {
        destinatarioField.setText(destinatario);
        assuntoField.setText(assunto);
        msgField.setText(mensagem);
    }
}
