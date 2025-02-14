package com.acolyptos.inventory.database;

import io.github.cdimascio.dotenv.Dotenv;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoDB {

  // private static final String URI = dotenv.get("MONGO_URI");
  private static final String DB_NAME = "inventory_system";

  private static MongoClient mongoClient;
  private static MongoDatabase database;

  public static void establishConnection() {
    Dotenv dotenv = Dotenv.load();
    final String URI = dotenv.get("MONGO_URI");

    try {
      mongoClient = MongoClients.create(URI);
      System.out.println("Succesfully Connected to the MongoDB Atlas.");
    } catch (Exception e) {
      System.out.println("Connection estalishment Failed.");
      System.out.println(e.getMessage());
    }
  }

  public static MongoDatabase getDatabase() {
    Dotenv dotenv = Dotenv.load();
    final String URI = dotenv.get("MONGO_URI");

    if (mongoClient == null) {
      CodecRegistry pojoCodecRegistry = CodecRegistries
          .fromProviders(PojoCodecProvider.builder().automatic(true).build());

      CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
          MongoClientSettings.getDefaultCodecRegistry(),
          pojoCodecRegistry);

      MongoClientSettings settings = MongoClientSettings.builder()
          .applyConnectionString(new com.mongodb.ConnectionString(URI))
          .codecRegistry(codecRegistry)
          .build();

      mongoClient = MongoClients.create(settings);
      database = mongoClient.getDatabase(DB_NAME);

      System.out.println("Succesfully Connected to the MongoDB Atlas.");
    }
    return database;
  }

  public static void closeConnection() {
    if (mongoClient != null) {
      mongoClient.close();
      mongoClient = null;
      System.out.println("MongoDB connection closed.");
    }
  }
}
