package org.example;



import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ConcurrentWithdrawal {
    // Database connection details
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/jdbc";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    // Table name and column names
    private static final String TABLE_NAME = "accounts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_BALANCE = "balance";

    public static void main(String[] args) {
        // Create the "accounts" table if it doesn't exist
        createTable();

        // Simulate two users trying to withdraw amounts simultaneously
        Thread sathish = new Thread(() -> withdrawAmount("sathish", 10));
        Thread sathi= new Thread(() -> withdrawAmount("sathi", 10));
        sathish.start();
        sathi.start();
    }

    private static void createTable() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            // Create the "accounts" table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                    COLUMN_ID + " SERIAL PRIMARY KEY, " +
                    COLUMN_NAME + " VARCHAR(255), " +
                    COLUMN_BALANCE + " DECIMAL(10,2)" +
                    ")";
            statement.executeUpdate(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void withdrawAmount(String userName, double amount) {
        Connection connection = null;
        java.sql.PreparedStatement selectStatement = null;
        PreparedStatement updateStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Retrieve the current balance
            String selectQuery = "SELECT " + COLUMN_BALANCE + " FROM " + TABLE_NAME +
                    " WHERE " + COLUMN_NAME + " = ?";
            selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, userName);
            resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                double balance = resultSet.getDouble(COLUMN_BALANCE);

                // Check if there is enough balance to withdraw
                if (balance >= amount) {
                    // Log the withdrawal
                    logWithdrawal(userName, amount);

                    // Update the balance
                    double newBalance = balance - amount;
                    String updateQuery = "UPDATE " + TABLE_NAME +
                            " SET " + COLUMN_BALANCE + " = ?" +
                            " WHERE " + COLUMN_NAME + " = ?";
                    updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setDouble(1, newBalance);
                    updateStatement.setString(2, userName);
                    updateStatement.executeUpdate();

                    System.out.println(userName + " successfully withdrew " + amount);
                } else {
                    System.out.println(userName + " does not have enough balance to withdraw " + amount);
                }
            } else {
                System.out.println(userName + " does not exist in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the result set
                if (resultSet != null) {
                    resultSet.close();
                }
                // Close the select statement
                if (selectStatement != null) {

                    selectStatement.close();
                }
// Close the update statement
                if (updateStatement != null) {
                    updateStatement.close();
                }
// Close the connection
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void logWithdrawal(String userName, double amount) {
        // Perform logging here, such as writing to a log file or database
        System.out.println("Logging withdrawal - User: " + userName + ", Amount: " + amount);
    }
}