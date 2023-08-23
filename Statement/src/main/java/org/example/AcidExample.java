package org.example;


import java.sql.*;
import java.sql.Statement;

public class AcidExample {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/callablestatement";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";


    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Establish database connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false); // Start transaction

            // Step 1: Atomicity - Perform multiple operations as a single atomic unit
            java.sql.Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO employees (id, name, salary) VALUES (1, 'Ramesh', 5000)");
            statement.executeUpdate("UPDATE employees SET salary = 6000 WHERE id = 1");

            // Step 2: Consistency - Enforce integrity constraints
            statement.executeUpdate("INSERT INTO employees (id, name, salary) VALUES (2, 'sathish', 4000)");
            System.out.println("Two records are inserted into employees table...");

            // Step 3: Isolation - Concurrent transactions should not interfere with each other
            // In this example, we'll simulate a concurrent transaction by creating a new thread
            Thread concurrentThread = new Thread(() -> {
                try {
                    Connection concurrentConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                    concurrentConnection.setAutoCommit(false);
                    Statement concurrentStatement = concurrentConnection.createStatement();
                    concurrentStatement.executeUpdate("UPDATE employees SET salary = 5500 WHERE id = 1");
                    concurrentStatement.close();
                    concurrentConnection.commit();
                    concurrentConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            concurrentThread.start();
            concurrentThread.join(); // Wait for concurrent thread to finish

            // Step 4: Durability - Committed changes are permanent even in the event of system failures
            connection.commit();

            // Cleanup
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback(); // Rollback the transaction in case of an error
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}


