package Dao;

import Model.Admin;
import PadroesProjeto.BancoDadosSingleton;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class AdminDao {

    private MongoDatabase database;
    private MongoCollection<Document> adminCollection;

    public AdminDao() {
        this.database = BancoDadosSingleton.getInstancia().getDatabase();
        this.adminCollection = database.getCollection("admin"); // Nome da coleção
    }

    // Cria um documento de administrador a partir do objeto Administrador
    private Document adminToDocument(Admin admin) {
        return new Document("nome", admin.getNome())
                .append("email", admin.getEmail())
                .append("senha", admin.getSenha());
    }

    // Converte um documento para um objeto Administrador
    private Admin documentToAdmin(Document doc) {
        return new Admin(
                doc.getString("nome"),
                doc.getString("email"),
                doc.getString("senha")
        );
    }

    // Salva um novo administrador na base de dados
    public void saveAdmin(Admin admin) {
        Document doc = adminToDocument(admin);
        adminCollection.insertOne(doc);
    }

    // Encontra um administrador pelo email
    public Admin findAdminByEmail(String email) {
        Document query = new Document("email", email);
        MongoCursor<Document> cursor = adminCollection.find(query).iterator();
        try {
            if (cursor.hasNext()) {
                Document doc = cursor.next();
                return documentToAdmin(doc);
            }
            return null;
        } finally {
            cursor.close();
        }
    }

    // Atualiza um administrador existente na base de dados
    public void updateAdmin(Admin admin) {
        Document query = new Document("email", admin.getEmail());
        Document update = new Document("$set", adminToDocument(admin));
        adminCollection.updateOne(query, update);
    }

    // Remove um administrador da base de dados
    public void deleteAdmin(String email) {
        Document query = new Document("email", email);
        adminCollection.deleteOne(query);
    }

    public Admin findAdmin() {
        // Retorna o primeiro administrador encontrado na coleção
        MongoCursor<Document> cursor = adminCollection.find().iterator();
        try {
            if (cursor.hasNext()) {
                Document doc = cursor.next();
                return documentToAdmin(doc);
            }
            return null;
        } finally {
            cursor.close();
        }
    }

}
