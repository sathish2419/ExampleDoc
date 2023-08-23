package org.example;


import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatement {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Connection connection = null;
        java.sql.PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish a connection to the PostgreSQL database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Sathish", "postgres", "root");

            // Prepare the SQL statement with parameters
            String sql = "SELECT * FROM student WHERE student_id = ?";
            statement = connection.prepareStatement(sql);

            // Set the parameter value
            System.out.print("Enter the student_id: ");
            int customerId = scanner.nextInt();
            statement.setInt(1, customerId);

            // Execute the query
            resultSet = statement.executeQuery();

            // Process the result
            while (resultSet.next()) {
                int resultId = resultSet.getInt("student_id");
                String First_name = resultSet.getString("First_name");
                String Last_name = resultSet.getString("Last_name");
                int Age = resultSet.getInt("Age");


                System.out.println("student_ID: " + resultId + ", First_name: " + First_name + ", Last_name: " + Last_name + ", Age: " + Age  );
            }

            System.out.println("Query executed successfully using PreparedStatement.");
        } catch (SQLException e) {
            System.out.println("Failed to execute the query using PreparedStatement.");
            e.printStackTrace();
        } finally {
            // Close the ResultSet, PreparedStatement, and Connection
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close the ResultSet.");
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close the PreparedStatement.");
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close the database connection.");
                    e.printStackTrace();
                }
            }
        }

        scanner.close();
    }
}