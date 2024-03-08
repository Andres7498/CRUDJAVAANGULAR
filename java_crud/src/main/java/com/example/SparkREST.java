package com.example;

import java.sql.SQLException;

import spark.Spark;

public class SparkREST {
  public static void main(String[] args) {
    Spark.get("/hello", (req, res) -> "Hello World");

    
    Spark.get("/person", (req, res) -> {
      try {
        String json = CRUDPersons.readPersons(PostgreSQLConnection.getConnection());
        res.status(200); 
        res.type("application/json");
        return json;
      } catch (SQLException e) {
        e.printStackTrace(); 
        res.status(500); 
        return "Error retrieving persons: " + e.getMessage();
      }
    });

    Spark.post("/person", (req, res) -> {
      String response = "Person creation not yet implemented!";
      res.status(201); 
      return response;
    });
  }
}
