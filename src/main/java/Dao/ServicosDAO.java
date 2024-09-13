package Dao;


import com.mongodb.client.MongoCollection;
//Dao/ServicosDAO.java
//Dao/ServicosDAO.java
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import Dto.ServicosDisponiveisDto;
import FactoryMetode.FabricaServico;
import Model.ServicosDisponiveis;
import PadroesProjeto.BancoDadosSingleton;

import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class ServicosDAO {
 private MongoDatabase database;
 private MongoCollection<Document> collection;

 public ServicosDAO() {
     database = BancoDadosSingleton.getInstancia().getDatabase();
     collection = database.getCollection("servicosDisponiveis");
 }

 public void adicionarServico(FabricaServico fabrica, ServicosDisponiveisDto servicoDTO) {
     ServicosDisponiveis servico = fabrica.criarServico(servicoDTO.getNome(), servicoDTO.getCusto());
     Document doc = new Document("nome", servico.getNome())
                     .append("custo", servico.getCusto())
                     .append("tipo", servicoDTO.getTipo());
     collection.insertOne(doc);
 }

 public List<Document> getServicos() {
     List<Document> servicos = new ArrayList<>();
     MongoCursor<Document> cursor = collection.find().iterator();
     while (cursor.hasNext()) {
         servicos.add(cursor.next());
     }
     return servicos;
 }
}
