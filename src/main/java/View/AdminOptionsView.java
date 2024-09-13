package View;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import Controller.AgendamentoController;
import Dto.ServicoDTO;
import PadraoBuiderRelatorio.PDFReportBuilder;
import PadraoBuiderRelatorio.ReportBuilderDirector;

public class AdminOptionsView extends JFrame {
    private AgendamentoController agendamentoController;
    private JTable servicosTable;

    public AdminOptionsView() {
        super("Opções do Administrador");
        agendamentoController = new AgendamentoController();
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        atualizarServicos(); // Atualiza a lista de serviços ao iniciar
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true); // Torna a janela visível
    }

    private void initComponents() {
        setLayout(new BorderLayout()); // Usando BorderLayout para posicionamento automático

        // Cria a tabela e define o modelo
        servicosTable = new JTable();
        servicosTable.setModel(new DefaultTableModel(
                new Object[]{"Cliente", "E-mail", "Tipo de Serviço", "Tipo", "Horário", "Ordem", "Ação"},
                0
        ));
        JScrollPane scrollPane = new JScrollPane(servicosTable);
        add(scrollPane, BorderLayout.CENTER);

        // Botão para confirmar o agendamento
        ButtonColumn buttonColumn = new ButtonColumn(servicosTable, 6);

        // Painel inferior para os botões
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Botão de relatório
        JButton relatorioButton = new JButton("Gerar Relatório");
        relatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorio();
            }
        });
        bottomPanel.add(relatorioButton);

        // Botão para cadastrar serviços
        JButton cadastrarServicosButton = new JButton("Cadastrar Serviços");
        cadastrarServicosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                new ServicoView().setVisible(true);
            }
        });
        bottomPanel.add(cadastrarServicosButton);

        // Adiciona o painel inferior à janela
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void atualizarServicos() {
        List<ServicoDTO> servicos = agendamentoController.listarServicosAgendados();
        DefaultTableModel model = (DefaultTableModel) servicosTable.getModel();
        model.setRowCount(0); // Limpa a tabela antes de atualizar

        for (ServicoDTO servico : servicos) {
            model.addRow(new Object[]{
                servico.getNomeCliente(),
                servico.getEmail(),
                servico.getTipoServico(),
                servico.isCaro() ? "Caro" : "Barato",
                servico.getHorario(),
                servico.getOrdem(),
                "Confirmar"
            });
        }
    }

    private void gerarRelatorio() {
        List<ServicoDTO> servicos = agendamentoController.listarServicosAgendados();

        if (servicos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum serviço agendado encontrado.", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        PDFReportBuilder pdfBuilder = new PDFReportBuilder();
        ReportBuilderDirector director = new ReportBuilderDirector(pdfBuilder);

        byte[] pdfBytes = director.constructReport("Relatório de Agendamentos", servicos, "Fim do Relatório");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Relatório em PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos PDF", "pdf"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (!fileToSave.getName().endsWith(".pdf")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
            }

            try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
                fos.write(pdfBytes);
                fos.flush();
                JOptionPane.showMessageDialog(this, "Relatório salvo com sucesso em: " + fileToSave.getAbsolutePath(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao salvar o relatório: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Classe interna para adicionar um botão à tabela
    class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
        private JTable table;
        private JButton renderButton;
        private JButton editorButton;
        private String label;
        private int column;

        public ButtonColumn(JTable table, int column) {
            this.table = table;
            this.column = column;
            renderButton = new JButton("Confirmar");
            renderButton.setOpaque(true);
            renderButton.addActionListener(this);
            editorButton = new JButton("Confirmar");
            editorButton.setOpaque(true);
            editorButton.addActionListener(this);

            // Adiciona o botão à coluna especificada
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            tableColumn.setCellRenderer(this);
            tableColumn.setCellEditor(this);
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                renderButton.setForeground(table.getSelectionForeground());
                renderButton.setBackground(table.getSelectionBackground());
            } else {
                renderButton.setForeground(table.getForeground());
                renderButton.setBackground(UIManager.getColor("Button.background"));
            }
            renderButton.setText((value == null) ? "" : value.toString());
            return renderButton;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            editorButton.setText(label);
            return editorButton;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int row = table.convertRowIndexToModel(table.getEditingRow());
            ServicoDTO servico = agendamentoController.listarServicosAgendados().get(row);
            confirmarAgendamento(servico);
        }
    }

    private void confirmarAgendamento(ServicoDTO servico) {
        // Exibe uma mensagem de confirmação
        JOptionPane.showMessageDialog(this, "Agendamento confirmado para: " + servico.getNomeCliente());

        // Abre a janela de envio de e-mail com os campos pré-preenchidos
        EmailApp emailApp = new EmailApp();
        emailApp.preencherCamposEmail(servico.getEmail(), "Confirmação de Agendamento", "Olá " + servico.getNomeCliente() + ",\nSeu agendamento foi confirmado para o horário: " + servico.getHorario());
        emailApp.setVisible(true);
    }
}
