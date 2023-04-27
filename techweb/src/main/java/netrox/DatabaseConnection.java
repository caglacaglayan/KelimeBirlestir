package netrox;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseConnection {
    public static MongoClient client;
    public static MongoDatabase db;
    public static MongoCollection col;

    public DatabaseConnection() {
        client = MongoClients.create("mongodb+srv://cagla:admin@kelimebirlestir.qva1jga.mongodb.net/?retryWrites=true&w=majority");
        db = client.getDatabase("sampleDB");
        col = db.getCollection("sampleCollection");
    }

    public void CloseConnection() {
        client.close();
    }

    public void OpenConnection() {
        client = MongoClients.create("mongodb+srv://cagla:admin@kelimebirlestir.qva1jga.mongodb.net/?retryWrites=true&w=majority");
        db = client.getDatabase("sampleDB");
        col = db.getCollection("sampleCollection");
    }
}