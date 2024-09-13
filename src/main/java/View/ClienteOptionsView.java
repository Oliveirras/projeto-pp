package View;

import Controller.ClienteController;
import Dto.DtoCliente;

import javax.swing.*;
import java.awt.*;

//View/ClienteOptionsView.java

import javax.swing.*;
import java.awt.*;

public class ClienteOptionsView extends ViewPrototype {
 private ClienteController clienteController;
 private JTextField emailField;
 private JTextField telefoneField;
 private JTextField enderecoField;
 private JPasswordField senhaField;

 public ClienteOptionsView() {
     super("Opções do Cliente");
     clienteController = new ClienteController();
     setSize(600, 600);
     setLocationRelativeTo(null);
     initComponents();
     setVisible(true);
 }


 @Override
 protected void initComponents() {
     imagemDeFundo();

     JButton agendarButton = new JButton("Agendar Serviço");
     agendarButton.setBounds(300, 280, 180, 30);
     agendarButton.addActionListener(e -> {
         JOptionPane.showMessageDialog(null, "Abrindo a tela de agendamento.");
         dispose();
         new AgendamentoView().setVisible(true);
     });
     add(agendarButton);

     JButton formaPagamentoButton = new JButton("Forma de Pagamento");
     formaPagamentoButton.setBounds(90, 280, 180, 30);
     formaPagamentoButton.addActionListener(e -> {
         dispose();
         PagamentoView view = new PagamentoView();
         view.setVisible(true);
     });
     add(formaPagamentoButton);

     JButton removerButton = new JButton("Remover Cliente");
     removerButton.setBounds(90, 320, 180, 30);
     removerButton.addActionListener(e -> {
         String id = JOptionPane.showInputDialog(null, "Digite o ID do cliente para remover:");
         if (id != null && !id.trim().isEmpty()) {
             clienteController.removerCliente(id);
             JOptionPane.showMessageDialog(this, "Cliente removido com sucesso.");
         } else {
             JOptionPane.showMessageDialog(this, "ID do cliente não pode estar vazio.");
         }
     });
     add(removerButton);

     JButton atualizarButton = new JButton("Atualizar Cliente");
     atualizarButton.setBounds(300, 320, 180, 30);
     atualizarButton.addActionListener(e -> {
         String id = JOptionPane.showInputDialog(this, "Digite o ID do cliente para atualizar:");
         if (id != null && !id.trim().isEmpty()) {
             DtoCliente clienteExistente = clienteController.mostrarClientePorId(id);
             if (clienteExistente != null) {
                 String email = emailField.getText().trim().isEmpty() ? clienteExistente.getEmail() : emailField.getText();
                 String telefone = telefoneField.getText().trim().isEmpty() ? clienteExistente.getTelefone() : telefoneField.getText();
                 String endereco = enderecoField.getText().trim().isEmpty() ? clienteExistente.getEndereco() : enderecoField.getText();
                 String senha = new String(senhaField.getPassword()).trim().isEmpty() ? clienteExistente.getSenha() : new String(senhaField.getPassword());

                 boolean sucesso = clienteController.atualizarCliente(id, clienteExistente.getNome(), email, telefone, endereco, senha);
                 if (sucesso) {
                     JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso.");
                 } else {
                     JOptionPane.showMessageDialog(this, "Erro ao atualizar o cliente.");
                 }
             } else {
                 JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
             }
         } else {
             JOptionPane.showMessageDialog(this, "ID do cliente não pode estar vazio.");
         }
     });
     add(atualizarButton);

     JButton mostrarButton = new JButton("Mostrar Cliente");
     mostrarButton.setBounds(90, 360, 180, 30);
     mostrarButton.addActionListener(e -> {
         String id = JOptionPane.showInputDialog(null, "Digite o ID do cliente para mostrar:");
         if (id != null && !id.trim().isEmpty()) {
             DtoCliente cliente = clienteController.mostrarClientePorId(id);
             if (cliente != null) {
                 ClienteInfoView clienteInfoView = new ClienteInfoView(cliente);
                 clienteInfoView.setVisible(true);
             } else {
                 JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
             }
         } else {
             JOptionPane.showMessageDialog(this, "ID do cliente não pode estar vazio.");
         }
     });
     add(mostrarButton);

     JButton voltarButton = new JButton("Voltar");
     voltarButton.setBounds(90, 400, 180, 30);
     voltarButton.addActionListener(e -> {
         dispose();
         new ClienteLoginView().setVisible(true);
     });
     add(voltarButton);

     JButton servicosButton = new JButton("Serviços Ofertados");
     servicosButton.setBounds(300, 360, 180, 30);
     servicosButton.addActionListener(e -> {
         dispose();
         new ServicosOfertadosView().setVisible(true); // Novo JFrame para exibir os serviços
     });
     add(servicosButton);

     emailField = new JTextField();
     telefoneField = new JTextField();
     enderecoField = new JTextField();
     senhaField = new JPasswordField();

     addLabelAndField("Novo Email:", emailField, 50, 150, 230, 25);
     addLabelAndField("Telefone:", telefoneField, 50, 180, 230, 25);
     addLabelAndField("Endereço:", enderecoField, 50, 210, 230, 25);
     addLabelAndField("Nova Senha:", senhaField, 50, 240, 230, 25);

     adicionarTitulo();
     adicionarImagem();
 }

 private void adicionarImagem() {
     ImageIcon icone = new ImageIcon("C:\\Users\\Glêisson\\Desktop\\ProjetoManicureFinal0.1\\ProjetoManicureEpedicure\\src\\main\\java\\View\\Imagens\\ImagemOpçoesDoCliente.png");
     JLabel lbImagem = new JLabel(icone, JLabel.CENTER);
     add(lbImagem);
     lbImagem.setBounds(430,300,160,200);
     lbImagem.setOpaque(true);
 }

 private void adicionarTitulo() {
     JLabel lbTitulo = new JLabel("Studio Linda");
     lbTitulo.setBounds(120,20,390,150);
     lbTitulo.setFont(new Font("Times new roman", Font.PLAIN,70));
     lbTitulo.setForeground(Color.DARK_GRAY);
     add(lbTitulo);
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
