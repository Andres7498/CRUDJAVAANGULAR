package com.example;

import java.sql.*;


public class PostgreSQLConnection {

    private static final String USER = "postgres"; 
    private static final String PASSWORD = "1234";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/javaCRUD";
    private static final String DRIVER_CLASS = "org.postgresql.Driver";

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName(DRIVER_CLASS);
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                System.out.println("Connection established successfully!"); 
            } catch (ClassNotFoundException e) {
                throw new SQLException("Error loading JDBC driver: " + e.getMessage());
            } catch (SQLException e) {
                throw new SQLException("Connection failed: " + e.getMessage());
            }
        }

        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
            System.out.println("Connection closed successfully!"); 
        }
    }
}
