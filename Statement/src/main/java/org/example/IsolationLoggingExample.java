package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class IsolationLoggingExample {



    private static final String DB_URL = "jdbc:postgresql://localhost:5432/jdbc";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            int accountId = 1;
            int initialBalance = getAccountBalance(connection, accountId);
            System.out.println("Initial balance: " + initialBalance);

            int newBalance = initialBalance + 100;
            System.out.println("Updating balance to: " + newBalance);

            // Log the transaction before performing the update
            logTransaction(connection, accountId, initialBalance, newBalance);

            updateAccountBalance(connection, accountId, newBalance);

            // Commit the transaction
            connection.commit();

            int finalBalance = getAccountBalance(connection, accountId);
            System.out.println("Final balance: " + finalBalance);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getAccountBalance(Connection connection, int accountId) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT balance FROM accounts WHERE id = " + accountId;
            return executeQueryAndRetrieveIntResult(statement, query);
        }
    }

    private static void updateAccountBalance(Connection connection, int accountId, int newBalance) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String query = "UPDATE accounts SET balance = " + newBalance + " WHERE id = " + accountId;
            statement.executeUpdate(query);
        }
    }

    private static void logTransaction(Connection connection, int accountId, int initialBalance, int newBalance) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String query = "INSERT INTO transaction_log (account_id, initial_balance, new_balance) VALUES (" +
                    accountId + ", " + initialBalance + ", " + newBalance + ")";
            statement.executeUpdate(query);
        }
    }

    private static int executeQueryAndRetrieveIntResult(Statement statement, String query) throws SQLException {
        int result = 0;
        try {
            statement.execute(query);
            var resultSet = statement.getResultSet();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
}



