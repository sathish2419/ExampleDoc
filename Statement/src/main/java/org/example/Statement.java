package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class Statement {
    static final String DB_URL = "jdbc:postgresql://localhost/Sathish";
    static final String username = "postgres";
    static final String password = "root";
    static final String QUERY = "SELECT \"student_id\",\"age\", \"first_name\", \"last_name\" FROM student";

    public static void main(String[] args) {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, username, password);
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);) {
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("student_id: " + rs.getInt("student_id"));
                System.out.print(", age: " + rs.getInt("age") );
                System.out.print(", first_name: " + rs.getString("first_name"));
                System.out.println(", last_name: " + rs.getString("last_name"));
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
}

