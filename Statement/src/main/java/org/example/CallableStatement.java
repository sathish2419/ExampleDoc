package org.example;


import java.sql.*;

public class CallableStatement {



    public static void main(String[] args) {
        Connection connection = null;
        java.sql.CallableStatement callableStatement = null;

        try {
            // Establish the database connection
            String jdbcUrl = "jdbc:postgresql://localhost/Sathish";
            String username = "postgres";
            String password = "root";
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Prepare the stored procedure call
            String myprocedure = "{CALL myprocedure(?, ?, ?, ?)}";
            callableStatement = connection.prepareCall(myprocedure);


            // Set the input parameters for the stored procedure
            callableStatement.setInt(1, 5);
            callableStatement.setInt(2, 25);
            callableStatement.setString(3, "karthick");
            callableStatement.setString(4, "kumar");

            // Execute the stored procedure
            callableStatement.execute();

            System.out.println("Employee inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
