package View;

import Dao.ServicosDAO;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ServicosOfertadosView extends ViewPrototype {
    private JTable servicosTable;
    private ServicosDAO servicosDAO;

    public ServicosOfertadosView() {
        super("Serviços Ofertados");
        servicosDAO = new ServicosDAO();
        initComponents(); // Inicializando componentes específicos
        
        // Centralizando a janela após adicionar os componentes
        setLocationRelativeTo(null); 
    }

    @Override
    protected void initComponents() {
        setLayout(new BorderLayout());

        // Dados dos serviços
        List<Document> servicos = servicosDAO.getServicos(); // Método que você deve adicionar no DAO
        String[] columnNames = {"Nome", "Custo", "Tipo"};
        Object[][] data = new Object[servicos.size()][3];

        for (int i = 0; i < servicos.size(); i++) {
            Document doc = servicos.get(i);
            data[i][0] = doc.getString("nome");
            data[i][1] = doc.getDouble("custo");
            data[i][2] = doc.getString("tipo");
        }

        servicosTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(servicosTable);
        add(scrollPane, BorderLayout.CENTER);

        // Botão Voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            dispose(); // Fecha a janela atual
            new ClienteOptionsView().setVisible(true); // Abre a janela ClienteOptionsView
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(voltarButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
