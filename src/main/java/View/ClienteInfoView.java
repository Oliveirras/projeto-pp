package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Dto.DtoCliente;
import java.awt.*;

public class ClienteInfoView extends ViewPrototype {

    public ClienteInfoView(DtoCliente cliente) {
        super("Informações do Cliente");
        setSize(600, 400);
        initComponents(cliente);
        setVisible(true);
    }

    @Override
    protected void initComponents() {
        // Não precisa implementar aqui, pois será feito no construtor
    }

    private void initComponents(DtoCliente cliente) {
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] colunas = {"Campo", "Valor"};
        String[][] dados = {
            {"ID", cliente.getId()},
            {"Nome", cliente.getNome()},
            {"Email", cliente.getEmail()},
            {"Telefone", cliente.getTelefone()},
            {"Endereço", cliente.getEndereco()},
            {"Senha", cliente.getSenha()}
        };

        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(model);
        tabela.setFillsViewportHeight(true); // Ajusta a tabela para preencher o painel de rolagem

        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);
    }
}
