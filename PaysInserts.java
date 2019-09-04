/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essai;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author Dodoma
 */
public class PaysInserts {

    public static void main(String[] args) {

        String lsDBName = "pays";
        String lsCollectionName = "pays";

        try {
            // Connexion a mongoDB
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

            // La BD
            MongoDatabase db = mongoClient.getDatabase(lsDBName);

            // La collection
            MongoCollection collection = db.getCollection(lsCollectionName);

            // Le document à ajouter
            Document pays1 = new Document();
            pays1.append("code", 12);
            pays1.append("nomPays", "Maroc");
            pays1.append("Capitale", "Rabat");

            Document pays2 = new Document();
            pays2.append("code", 13);
            pays2.append("nomPays", "Tunisie");
            pays2.append("Capitale", "Tunis");

            List<Document> list = new ArrayList();
            list.add(pays1);
            list.add(pays2);

            // Ajout (void)
            collection.insertMany(list);
            System.out.println("Nouveaux pays ajoutés !");

            /*
            MAPS 2 MongoDBCollection
             */
            List<Map<String, String>> listMaps = new ArrayList();
            Map<String, String> map1 = new LinkedHashMap();
            map1.put("nom", "Algérie");
            map1.put("capitale", "Alger");
            Map<String, String> map2 = new LinkedHashMap();
            map2.put("nom", "Corse");
            map2.put("capitale", "Ajaccio");
            listMaps.add(map1);
            listMaps.add(map2);

            List<Document> documentList = new ArrayList();
            Document doc;
            for (int i = 0; i < listMaps.size(); i++) {
                Map<String, String> map = listMaps.get(i);
                doc = new Document();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    doc.put(key, value);
                }
                documentList.add(doc);
            }

            collection.insertMany(documentList);

            // Fermeture de la connexion au serveur
            mongoClient.close();
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    } /// main
}
