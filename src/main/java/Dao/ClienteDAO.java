package Dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import PadroesProjeto.BancoDadosSingleton;
import Dto.DtoCliente;

public class ClienteDAO {
    private MongoDatabase database;

    public ClienteDAO() {
        this.database = BancoDadosSingleton.getInstancia().getDatabase();
    }

    public String inserirCliente(DtoCliente dtoCliente) {
        MongoCollection<Document> colecao = database.getCollection("clientes");

        Document documento = new Document("nome", dtoCliente.getNome())
                .append("email", dtoCliente.getEmail())
                .append("telefone", dtoCliente.getTelefone())
                .append("endereco", dtoCliente.getEndereco())
                .append("senha", dtoCliente.getSenha());  // Adiciona a senha ao documento

        colecao.insertOne(documento);

        return documento.getObjectId("_id").toHexString(); // Retorna o ID gerado
    }

    public void removerCliente(String id) {
        MongoCollection<Document> colecao = database.getCollection("clientes");

        try {
            Bson filtro = Filters.eq("_id", new org.bson.types.ObjectId(id)); // Converte o ID para ObjectId
            colecao.deleteOne(filtro);
        } catch (Exception e) {
            // Lidar com exceções, se necessário
            e.printStackTrace();
        }
    }

 // Atualizar o método de atualização no ClienteDAO
    public boolean atualizarCliente(DtoCliente dtoCliente) {
        MongoCollection<Document> colecao = database.getCollection("clientes");

        try {
            Bson filtro = Filters.eq("_id", new org.bson.types.ObjectId(dtoCliente.getId())); // Converte o ID para ObjectId

            Document documentoAtualizado = new Document("nome", dtoCliente.getNome())
                    .append("email", dtoCliente.getEmail())
                    .append("telefone", dtoCliente.getTelefone())
                    .append("endereco", dtoCliente.getEndereco())
                    .append("senha", dtoCliente.getSenha());  // Atualiza a senha também

            Document documentoAtualizacao = new Document("$set", documentoAtualizado);

            UpdateResult resultado = colecao.updateOne(filtro, documentoAtualizacao);
            
            if (resultado.getMatchedCount() == 0) {
                System.out.println("Nenhum cliente foi encontrado com o ID especificado.");
                return false;
            } else if (resultado.getModifiedCount() == 0) {
                System.out.println("O cliente foi encontrado, mas os dados não foram modificados.");
                return false;
            }

            return true;  // Retorna true se o cliente foi atualizado com sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retorna false se ocorrer algum erro
        }
    }


    public DtoCliente mostrarClientePorId(String id) {
        MongoCollection<Document> colecao = database.getCollection("clientes");

        try {
            Bson filtro = Filters.eq("_id", new org.bson.types.ObjectId(id)); // Converte o ID para ObjectId
            Document documento = colecao.find(filtro).first();

            if (documento != null) {
                return new DtoCliente(
                    documento.getObjectId("_id").toHexString(),  // Converte o ObjectId para String
                    documento.getString("nome"),
                    documento.getString("email"),
                    documento.getString("telefone"),
                    documento.getString("endereco"),
                    documento.getString("senha")  // Inclui a senha no DTO
                );
            } else {
                System.out.println("Nenhum cliente encontrado com o ID fornecido.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;  // Retorna null se o cliente não for encontrado
    }


    public DtoCliente buscarClientePorEmail(String email) {
        MongoCollection<Document> colecao = database.getCollection("clientes");

        try {
            Bson filtro = Filters.eq("email", email);  // Filtra pelo campo "email"
            Document documento = colecao.find(filtro).first();  // Busca o primeiro documento correspondente

            if (documento != null) {
                return new DtoCliente(
                    documento.getObjectId("_id").toHexString(),  // Converte o ObjectId para String
                    documento.getString("nome"),
                    documento.getString("email"),
                    documento.getString("telefone"),
                    documento.getString("endereco"),
                    documento.getString("senha")  // Inclui a senha no DTO
                );
            }
        } catch (Exception e) {
            // Lidar com exceções, se necessário
            e.printStackTrace();
        }

        return null;  // Retorna null se o cliente não for encontrado
    }
}
