package ru.otus.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Created by tully.
 * <p>
 * Commands
 * terminal: mongo
 * <p>
 * In mongo shell:
 * show dbs                     		show database names
 * show collections             	    show collections in current database
 * show users                   		show users in current database
 * show profile                 		show most recent system.profile entries with time >= 1ms
 * show logs                    		show the accessible logger names
 * show log [name]              	    prints out the last segment of log in memory, 'global' is default
 * use <db_name>                	    set current database
 * db.foo.find()                		list objects in collection foo
 * db.foo.find( { a : 1 } )     	    list objects in foo where a == 1
 * exit                         		quit the mongo shell
 * <p>
 * Example:
 * use local
 * db.system.indexes.find()
 */
@SuppressWarnings("WeakerAccess")
public class MongoMain {
    private final MongoClient mongo;
    private final MongoDatabase mongoDatabase;

    MongoMain() {
        mongo = new MongoClient("localhost", 27017);
        mongoDatabase = mongo.getDatabase("testdb"); //create if not exist
    }

    public static void main(String[] args) {
        //org.apache.log4j.BasicConfigurator.configure();

        MongoMain main = new MongoMain();
        main.example();
    }

    private void example() {
        listDatabaseNames();

        /*
        String collectionName = "user";

        insert(collectionName, new Document("name", "Tully").append("company", "Umbrella INC"));

        FindIterable<Document> iterable = findByName(collectionName, "Tully");

        System.out.println("\nFind by name result: ");
        for (Document document : iterable) {
            System.out.println(document);
        }

        ObjectId id = insert(collectionName, new Document("name", "Sully").append("age", 42));

        FindIterable<Document> iterableID = findById(collectionName, id);

        System.out.println("\nFind by id result: ");
        for (Document document : iterableID) {
            System.out.println(document);
        }
        */
        mongo.close();
    }

    private FindIterable<Document> findById(String collectionName, ObjectId id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", id);
        return collection.find(searchQuery);
    }

    private FindIterable<Document> findByName(String collectionName, String userName) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", userName);
        return collection.find(searchQuery);
    }

    private void listDatabaseNames() {
        MongoIterable<String> dbs = mongo.listDatabaseNames();
        System.out.println("\nDatabase names:");
        for (String db : dbs) {
            System.out.println(db);
        }
    }

    private ObjectId insert(String collectionName, Document doc) {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName); //creates if not exist
        collection.insertOne(doc);
        return (ObjectId) doc.get("_id");
    }
}
