package main.java.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtil {

   // private static final String DATABASE_URL = "jdbc:mysql://server.smcs-stuproj00.city.ac.uk:3306/in2033t25";
   private static final String DATABASE_URL = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t25";

    private static final String USERNAME = "in2033t25_d";
    private static final String PASSWORD = "3QvAOwhLS40";


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found");
            e.printStackTrace();

            System.exit(1);
        }
    }


    public static Connection getConnection() { // setting up the connection to the database
        try {
            System.out.println("Connecting to the database...");
            return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Could not connect to the database");
            e.printStackTrace();

            return null; // returns null if connection failed
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Connection to the database was successful!");

            // test the insert operation
            testInsert();


            closeConnection(conn);   // closing of  the connection
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }


    public static void closeConnection(Connection conn) { // for the closure of the connections
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.out.println("Failed to close the connection.");
                e.printStackTrace();
            }
        }
    }

    public static void testInsert() {
        String sql = "INSERT INTO StaffMember (StaffForename, StaffSurname) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement statementWhichIsPrepared = conn.prepareStatement(sql)) {


            statementWhichIsPrepared.setString(1, "Taha");// setting the pararmeters for testing
            statementWhichIsPrepared.setString(2, "Choksi");

            int affectedRows = statementWhichIsPrepared.executeUpdate();
            System.out.println("Inserted successfully, affected rows: " + affectedRows);

        } catch (SQLException e) {
            System.out.println("Insert operation failed.");
            e.printStackTrace();
        }
    }
}

