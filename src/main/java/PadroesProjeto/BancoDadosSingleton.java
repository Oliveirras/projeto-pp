package PadroesProjeto;

import com.mongodb.client.MongoClient;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class BancoDadosSingleton  {
    private static BancoDadosSingleton instancia;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private BancoDadosSingleton() {
        // Construtor privado para evitar instanciamento externo
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("configuracao22"); // Nome do banco de dados
    }

    public static synchronized BancoDadosSingleton getInstancia() {
        if (instancia == null) {
            instancia = new BancoDadosSingleton();
        }
        return instancia;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    // Método para fechar a conexão, se necessário
    public void fecharConexao() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
