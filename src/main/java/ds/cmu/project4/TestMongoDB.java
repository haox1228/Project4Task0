package ds.cmu.project4;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Date;
import java.util.Scanner;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class TestMongoDB {
    public static void main(String[] args) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        ConnectionString connectionString = new ConnectionString("mongodb+srv://jingqiz:ds2022F@cluster0.5sahucz.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("test").withCodecRegistry(pojoCodecRegistry);
        ;
        MongoCollection<Document> collection = database.getCollection("log");

        // Prompt the user for a string
        System.out.println("Please input a string to write into MongoDB: ");
        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine();

        // Write the string as part of a document to the mongoDB database
        Document doc = new Document()
                .append("string", user_input)
                .append("timestamp", new Date(System.currentTimeMillis()));
        collection.insertOne(doc);

        // Read all documents currently stored in the database and print all strings contained in the documents to the console
        System.out.println("Retrieve all logs in MongoDB:");
        collection.find().forEach(log -> System.out.println(log.get("string")));

//        Bson filter = Filters.eq("string", "hello");
//        collection.deleteMany(filter);
    }
}
