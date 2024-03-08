package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CRUDPersons {


    
    public static void InsertPersons() {

        Connection connection = null;
        try {
            connection = PostgreSQLConnection.getConnection();

            insertRow(connection, "Pio Kulkarni", "1", "1998-04-01");
            insertRow(connection, "Brigit Carrillo", "2", "1998-04-02");
            insertRow(connection, "Ozi Favager", "3", "1998-04-03");
            insertRow(connection, "Evelina Iordanou", "4", "1998-04-04");
            insertRow(connection, "Mahmud Ó Riagáin", "5", "1998-04-05");

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }

    public static void insertRow(Connection connection, String fullName, String idNumber, String dateBirth)
            throws SQLException {
        String sql = "INSERT INTO persons (fullname, id_number, datebirth) VALUES (?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, fullName);
        stmt.setString(2, idNumber);
        java.sql.Date birthDate = java.sql.Date.valueOf(dateBirth);
        stmt.setDate(3, birthDate);

        stmt.executeUpdate();
        System.out.println("Inserted successfully!");

    }

    public static String readPersons(Connection connection) throws SQLException, JsonProcessingException  {
        String sql = "SELECT * FROM persons";
        PreparedStatement stmt = connection.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();
        
        List<Person> persons = new ArrayList<>();

        System.out.println("\n-- All Persons --");
        while (rs.next()) {
            String name = rs.getString("fullname");
            String idNum = rs.getString("id_number");
            Date birthDate = rs.getDate("datebirth");
            System.out.println("Name: " + name + ", ID Number: " + idNum + ", Date of Birth: " + birthDate);
        }

        rs.close();
        stmt.close();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(persons);
        return json;
    }

    public static void updatePerson(Connection connection, String fullName, String id_number, String dateBirth)
            throws SQLException {
        String sql = "UPDATE persons SET fullname = ?, id_number = ?, datebirth = ? WHERE persons.id_number = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, fullName);
        stmt.setString(2, id_number);
        java.sql.Date birthDate = java.sql.Date.valueOf(dateBirth);
        stmt.setDate(3, birthDate);
        stmt.setString(4, id_number);

        int rowsUpdated = stmt.executeUpdate();

        System.out.println("\n-- Update Person --");
        if (rowsUpdated > 0) {
            System.out.println("Person with ID " + id_number + " updated successfully!");
        } else {
            System.out.println("No person found with ID " + id_number);
        }

        stmt.close();
    }

    public static void deletePerson(Connection connection, String id_number) throws SQLException {
        String sql = "DELETE FROM persons WHERE persons.id_number = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, id_number);

        int rowsDeleted = stmt.executeUpdate();

        System.out.println("\n-- Delete Person --");
        if (rowsDeleted > 0) {
            System.out.println("Person with ID " + id_number + " deleted successfully!");
        } else {
            System.out.println("No person found with ID " + id_number);
        }

        stmt.close();
    }
}
