package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LockingExample {

    // JDBC database URL, username, and password
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Sathish";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Establish the database connection
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Create the "accounts" table if it doesn't exist
             createAccountsTable(connection);

            // Set the transaction isolation level to SERIALIZABLE
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // Begin the transaction
            connection.setAutoCommit(false);

            // Perform insert and update operations using different locking mechanisms
            insertAndUpdateWithLock(connection);
            insertAndUpdateWithSelectForUpdate(connection);
            insertAndUpdateWithSerializableIsolation(connection);

            // Commit the transaction
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // Close the database connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void createAccountsTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String createTableQuery = "CREATE TABLE IF NOT EXISTS accounts ("
                + "id SERIAL PRIMARY KEY,"
                + "name VARCHAR(100) NOT NULL,"
                + "balance DECIMAL(10, 2) NOT NULL"
                + ")";
        statement.executeUpdate(createTableQuery);
        statement.close();
        System.out.println("Table 'accounts' created successfully.");
    }

    private static void insertAndUpdateWithLock(Connection connection) throws SQLException {
        System.out.println("Performing insert and update operations with LOCK TABLE...");
        Statement statement = connection.createStatement();

        // Acquire a table-level lock
        statement.execute("LOCK TABLE accounts IN ACCESS EXCLUSIVE MODE");

        // Perform insert operation
        String insertQuery = "INSERT INTO accounts (name, balance) VALUES ('User A', 1000.00)";
        statement.executeUpdate(insertQuery);

        // Perform update operation
        String updateQuery = "UPDATE accounts SET balance = balance + 100 WHERE name = 'User A'";
        statement.executeUpdate(updateQuery);

        // No explicit unlock statement required for table-level lock in PostgreSQL

        statement.close();
        System.out.println("Insert and update operations with LOCK TABLE completed successfully.");
    }


    private static void insertAndUpdateWithSelectForUpdate(Connection connection) throws SQLException {
        System.out.println("Performing insert and update operations with SELECT FOR UPDATE...");
        Statement statement = connection.createStatement();

        // Perform insert operation
        String insertQuery = "INSERT INTO accounts (name, balance) VALUES ('User B', 2000.00)";
        statement.executeUpdate(insertQuery);

        // Perform update operation with SELECT FOR UPDATE
        String updateQuery = "UPDATE accounts SET balance = balance + 200 WHERE name = 'User B'";
        statement.execute("BEGIN");
        statement.executeQuery("SELECT * FROM accounts WHERE name = 'User B' FOR UPDATE");
        statement.executeUpdate(updateQuery);
        statement.execute("COMMIT");

        statement.close();
        System.out.println("Insert and update operations with SELECT FOR UPDATE completed successfully.");
    }

    private static void insertAndUpdateWithSerializableIsolation(Connection connection) throws SQLException {
        System.out.println("Performing insert and update operations with SERIALIZABLE isolation...");

        // Create a new connection for the insert operation
        Connection insertConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        insertConnection.setAutoCommit(false);
        insertConnection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

        // Perform insert operation
        Statement insertStatement = insertConnection.createStatement();
        String insertQuery = "INSERT INTO accounts (name, balance) VALUES ('User C', 3000.00)";
        insertStatement.executeUpdate(insertQuery);
        insertConnection.commit();
        insertStatement.close();
        insertConnection.close();

        // Create another connection for the update operation
        Connection updateConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        updateConnection.setAutoCommit(false);
        updateConnection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

        // Perform update operation
        Statement updateStatement = updateConnection.createStatement();
        String updateQuery = "UPDATE accounts SET balance = balance + 300 WHERE name = 'User C'";
        updateStatement.executeUpdate(updateQuery);
        updateConnection.commit();
        updateStatement.close();
        updateConnection.close();

        System.out.println("Insert and update operations with SERIALIZABLE isolation completed successfully.");
    }
}
