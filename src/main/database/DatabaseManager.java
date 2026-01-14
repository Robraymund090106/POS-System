package main.database;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.management.Query;

import main.model.User;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:pos.db";

    // since we are working with an embedded database (SQLite), each device has its own local copy of the database.
    // hence hindi tayo makakashare ng one database
    // returns a Connection to the SQLite database file (creates file if it doesn't exist)
    private static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
            return null;
        }
    }

    // this method creates the tables for the database ensuring na lahat tayo have the same structure
    public static void initializeDatabase() {
    // We use try-with-resources to ensure the connection and statement close properly
    try (Connection conn = connect()) {
        if (conn != null) {
            Statement stmt = conn.createStatement();
            
            // This query creates the table with ALL columns matching your User class
            String sql = "CREATE TABLE IF NOT EXISTS User (" +
                         "userId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "username TEXT NOT NULL UNIQUE, " +
                         "password TEXT NOT NULL, " +
                         "email TEXT, " +
                         "role TEXT, " +
                         "isActive INTEGER DEFAULT 1, " +
                         "age INTEGER)";
            
            stmt.execute(sql);
            
            // OPTIONAL: Add a default admin if the table is empty
            String addAdmin = "INSERT OR IGNORE INTO User (username, password, role, email, age) " +
                              "VALUES ('admin', 'admin123', 'ADMIN', 'admin@pos.com', 25)";
            stmt.execute(addAdmin);
            
            System.out.println("Database checked/created successfully.");
        }
        } catch (SQLException e) {
            System.err.println("Database Init Error: " + e.getMessage());
        }
    }   

    public static boolean existsByUsername(String username) {
    String sql = "SELECT COUNT(*) FROM User WHERE username = ?";
    
    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {

            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        System.err.println("Existence check error: " + e.getMessage());
    }
    return false;
    }

    

    public static boolean checkPassword(String username, String password) {

        String sql = "SELECT password FROM User WHERE username = ?";

        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

        
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
        
                return storedPassword.equals(password);
            }
        } catch (SQLException e) {
            System.err.println("Password check error: " + e.getMessage());
        }
                return false; 
    }

}