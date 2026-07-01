package com.zerowaste.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database Connection Utility Class
 * Provides MySQL database connection for Zero Waste application
 * @author Zero Waste Team
 */
public class DBConnection {
    
    // Database configuration constants
    private static final String DB_URL = "jdbc:mysql://localhost:3306/zerowaste_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Jaideep@12";
    
    /**
     * Establishes and returns a database connection
     * @return Connection object if successful, null if failed
     */
    public static Connection getConnection() {
        System.out.println("DEBUG: Attempting to connect to database...");
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("DEBUG: MySQL JDBC Driver loaded successfully");
            
            // Establish connection
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("DEBUG: Database connection established successfully");
            
            return conn;
            
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: MySQL JDBC Driver not found - " + e.getMessage());
            e.printStackTrace();
            return null;
            
        } catch (SQLException e) {
            System.out.println("ERROR: Database connection failed - " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Closes the database connection
     * @param conn Connection object to close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("DEBUG: Database connection closed");
            } catch (SQLException e) {
                System.out.println("ERROR: Failed to close database connection - " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
