package main.java.database;

import java.sql.*;

public class DBC {
    public static void main(String[] args) {
        // Initialise parameters for the getConnection() method
        // !!! Insert relevant details below (i.e., localhost link, root) !!!
        String url = "";
        String username = "";
        String password = ""; // do not have a password => empty string

        // Register the driver class
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create the connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create the statement object
            Statement statement = connection.createStatement();

            // Execute the query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM allergen");

            // Display all the data (i.e., allergenID, nameOfAllergen, descriptionOfAllergen)
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2)
                                    + " " + resultSet.getString(3));
            }

            // Close the connection
            connection.close();
        }

        catch (Exception e) {
            System.out.println(e);
        }

    }
}
