package Dao;

import Dto.ServicoDTO;
import PadroesProjeto.BancoDadosSingleton;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {
    private MongoDatabase database;

    public ServicoDAO() {
        database = BancoDadosSingleton.getInstancia().getDatabase();
    }

    public void adicionarServico(ServicoDTO servicoDTO) {
        MongoCollection<Document> collection = database.getCollection("servicos");

        Document document = new Document("cliente", servicoDTO.getNomeCliente())
                .append("tipoServico", servicoDTO.getTipoServico())
                .append("caro", servicoDTO.isCaro())
                .append("email", servicoDTO.getEmail()) // Inclui o e-mail
                .append("ordem", servicoDTO.getOrdem()) // Inclui a ordem
                .append("horario", servicoDTO.getHorario()); // Inclui o horário
        collection.insertOne(document);
    }

    public List<ServicoDTO> listarServicos() {
        MongoCollection<Document> collection = database.getCollection("servicos");
        List<ServicoDTO> servicoDTOs = new ArrayList<>();

        for (Document doc : collection.find()) {
            ServicoDTO servicoDTO = new ServicoDTO(
                    doc.getString("tipoServico"),
                    doc.getBoolean("caro"),
                    doc.getString("cliente"),
                    doc.getString("email"), // Inclui o e-mail
                    doc.getInteger("ordem"), // Inclui a ordem
                    doc.getString("horario") // Inclui o horário
            );
            servicoDTOs.add(servicoDTO);
        }

        return servicoDTOs;
    }

    public int getNextOrder() {
        MongoCollection<Document> collection = database.getCollection("servicos");
        Document maxOrderDoc = collection.find().sort(new Document("ordem", -1)).first();

        if (maxOrderDoc != null) {
            return maxOrderDoc.getInteger("ordem") + 1;
        } else {
            return 1; // Inicia com a ordem 1 se nenhum documento existir
        }
    }

    // Novo método para verificar horários disponíveis
    public List<String> getHorariosDisponiveis() {
        MongoCollection<Document> collection = database.getCollection("servicos");
        List<String> horarios = new ArrayList<>();
        // Horários disponíveis das 7h às 18h
        for (int i = 7; i <= 18; i++) {
            String horario = String.format("%02d:00", i);
            horarios.add(horario);
        }
        // Remove os horários já ocupados
        for (Document doc : collection.find()) {
            String horario = doc.getString("horario");
            horarios.remove(horario);
        }
        return horarios;
    }
}
