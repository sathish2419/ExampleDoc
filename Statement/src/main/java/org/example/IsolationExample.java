package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class IsolationExample {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/jdbc";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            // Start the first transaction
            System.out.println("Transaction 1: Reading initial balance...");
            int initialBalance = getAccountBalance(connection);
            System.out.println("Transaction 1: Initial balance is " + initialBalance);

            // Sleep for a few seconds to simulate some processing time
            Thread.sleep(3000);

            // Start the second transaction concurrently
            System.out.println("Transaction 2: Reading initial balance...");
            int updatedBalance = getAccountBalance(connection);
            System.out.println("Transaction 2: Initial balance is " + updatedBalance);

            // Perform an update in the second transaction
            int newBalance = updatedBalance + 100;
            System.out.println("Transaction 2: Updating balance to " + newBalance);
            updateAccountBalance(connection, newBalance);

            // Commit the second transaction
            connection.commit();

            // Sleep again to allow the first transaction to continue
            Thread.sleep(3000);

            // Continue the first transaction
            System.out.println("Transaction 1: Reading updated balance...");
            int finalBalance = getAccountBalance(connection);
            System.out.println("Transaction 1: Updated balance is " + finalBalance);

            // Rollback the first transaction
            connection.rollback();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int getAccountBalance(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT balance FROM accounts WHERE id = 1");
        if (resultSet.next()) {
            return resultSet.getInt("balance");
        }
        return 0;
    }

    private static void updateAccountBalance(Connection connection, int newBalance) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE accounts SET balance = " + newBalance + " WHERE id = 1");
    }
}


