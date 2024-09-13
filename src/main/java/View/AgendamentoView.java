package View;

import Controller.AgendamentoController;
import Dto.ServicoDTO;
import Comand.AgendarServicoCommand;
import Comand.Chamador;
import Comand.ListarAgendamentosCommand;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AgendamentoView extends ViewPrototype {
    private JTextField tipoServicoField;
    private JTextField nomeClienteField;
    private JTextField emailField;
    private JCheckBox caroCheckBox;
    private JComboBox<String> horarioComboBox;
    private AgendamentoController agendamentoController;
    private Chamador chamador;

    public AgendamentoView() {
        super("Agendamento de Serviço");
        setSize(600, 600);
        agendamentoController = new AgendamentoController();
        chamador = new Chamador();  // Criar o invoker
        this.setLocationRelativeTo(null);
        imagemDeFundo();
        initComponents();
        setVisible(true);
    }


    @Override
    protected void initComponents() {
        tipoServicoField = new JTextField();
        nomeClienteField = new JTextField();
        emailField = new JTextField();
        caroCheckBox = new JCheckBox("Vip");

        setLayout(null);

        // Adicionar os campos ao layout
        addLabelAndField("Cliente:", nomeClienteField, 50, 220, 200, 25);
        addLabelAndField("E-mail:", emailField, 50, 250, 200, 25);
        addLabelAndField("Serviço:", tipoServicoField, 50, 280, 200, 25);

        // ComboBox de horários
        horarioComboBox = new JComboBox<>();
        atualizarHorariosDisponiveis();
        horarioComboBox.setBounds(140, 320, 150, 25);
        add(horarioComboBox);

        caroCheckBox.setBounds(140, 350, 100, 25);
        add(caroCheckBox);

        JButton agendarButton = new JButton("Agendar");
        agendarButton.setBounds(300, 390, 150, 30);
        agendarButton.addActionListener(e -> {
            // Criar um DTO para o serviço
            String tipoServico = tipoServicoField.getText();
            String nomeCliente = nomeClienteField.getText();
            String email = emailField.getText();
            boolean caro = caroCheckBox.isSelected();
            String horario = (String) horarioComboBox.getSelectedItem();

            if (tipoServico != null && !tipoServico.trim().isEmpty() && nomeCliente != null && !nomeCliente.trim().isEmpty() && email != null && !email.trim().isEmpty() && horario != null) {
                int ordem = agendamentoController.getProximaOrdem();
                ServicoDTO servicoDTO = new ServicoDTO(tipoServico, caro, nomeCliente, email, ordem, horario);

                // Definir o comando de agendamento
                chamador.setComando(new AgendarServicoCommand(agendamentoController, servicoDTO, this));
                chamador.executarComando();
            } else {
                JOptionPane.showMessageDialog(this, "Tipo de serviço, nome do cliente, e-mail e horário não podem estar vazios.");
            }
        });
        add(agendarButton);

        JButton listarButton = new JButton("Agendamentos");
        listarButton.setBounds(140, 390, 150, 30);
        listarButton.addActionListener(e -> {
            // Definir o comando de listagem
            chamador.setComando(new ListarAgendamentosCommand(agendamentoController, this));
            chamador.executarComando();
        });
        add(listarButton);

        // Botão Voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(140, 430, 150, 30);
        voltarButton.addActionListener(e -> {
            dispose(); // Fecha a janela atual
            new ClienteOptionsView().setVisible(true); // Abre a janela ClienteOptionsView
        });
        add(voltarButton);
        adicionarTitulo();
        adicionarImagem();
    }

    public void atualizarHorariosDisponiveis() {
        List<String> horariosDisponiveis = agendamentoController.listarHorariosDisponiveis();
        horarioComboBox.removeAllItems();
        for (String horario : horariosDisponiveis) {
            horarioComboBox.addItem(horario);
        }
    }
    private void adicionarTitulo() {
        JLabel lbTitulo = new JLabel("Studio Linda");
        lbTitulo.setBounds(120,10,390,100);
        lbTitulo.setFont(new Font("Times new roman", Font.PLAIN,70));
        lbTitulo.setForeground(Color.DARK_GRAY);
        add(lbTitulo);
    }
    private void adicionarImagem() {
        ImageIcon icone = new ImageIcon("C:\\Users\\Glêisson\\Desktop\\ProjetoManicureFinal0.1\\ProjetoManicureEpedicure\\src\\main\\java\\View\\Imagens\\Imagem200P120.png");
        JLabel lbImagem = new JLabel(icone, JLabel.CENTER);
        add(lbImagem);
        lbImagem.setBounds(100,100,200,120);
        lbImagem.setOpaque(true);
    }
    public void imagemDeFundo() {
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon fundo = new ImageIcon("C:\\Users\\Glêisson\\Desktop\\ProjetoManicureFinal0.1\\ProjetoManicureEpedicure\\src\\main\\java\\View\\Imagens\\ConfigCliente.jpg");
                g.drawImage(fundo.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        panel.setLayout(null);
        setContentPane(panel);
    }
}
