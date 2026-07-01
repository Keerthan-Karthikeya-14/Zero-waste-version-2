package com.zerowaste.dao;

import com.zerowaste.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User Data Access Object
 * Handles all database operations for user management
 * @author Zero Waste Team
 */
public class UserDAO {
    
    /**
     * Inserts a new user into the database
     * @param fullName User's full name
     * @param email User's email address
     * @param password User's hashed password
     * @param role User's role (ADMIN, DONOR, RECEIVER)
     * @return true if user inserted successfully, false otherwise
     */
    public boolean insertUser(String fullName, String email, String password, String role) {
        System.out.println("DEBUG: Attempting to insert new user - Email: " + email);
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Get database connection
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("ERROR: Failed to get database connection for user insertion");
                return false;
            }
            
            // SQL query to insert user
            String sql = "INSERT INTO users (full_name, email, password, role, created_at) VALUES (?, ?, ?, ?, NOW())";
            pstmt = conn.prepareStatement(sql);
            
            // Set parameters
            pstmt.setString(1, fullName);
            pstmt.setString(2, email);
            pstmt.setString(3, password); // In production, this should be hashed
            pstmt.setString(4, role);
            
            // Execute query
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("DEBUG: User inserted successfully - Email: " + email);
                return true;
            } else {
                System.out.println("ERROR: Failed to insert user - No rows affected");
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR: SQL Exception during user insertion - " + e.getMessage());
            e.printStackTrace();
            return false;
            
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                System.out.println("ERROR: Failed to close resources - " + e.getMessage());
            }
        }
    }
    
    /**
     * Finds a user by email address
     * @param email User's email address
     * @return User object if found, null otherwise
     */
    public User findUserByEmail(String email) {
        System.out.println("DEBUG: Attempting to find user by email - Email: " + email);
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // Get database connection
            conn = DBConnection.getConnection();
            if (conn == null) {
                System.out.println("ERROR: Failed to get database connection for user lookup");
                return null;
            }
            
            // SQL query to find user by email
            String sql = "SELECT id, full_name, email, password, role, created_at FROM users WHERE email = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            
            // Execute query
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Create User object from result set
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                
                System.out.println("DEBUG: User found successfully - Email: " + email + ", Role: " + user.getRole());
                return user;
                
            } else {
                System.out.println("DEBUG: No user found with email - Email: " + email);
                return null;
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR: SQL Exception during user lookup - " + e.getMessage());
            e.printStackTrace();
            return null;
            
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                System.out.println("ERROR: Failed to close resources - " + e.getMessage());
            }
        }
    }
    
    /**
     * User Model Class (inner class for simplicity)
     * Represents a user in the system
     */
    public static class User {
        private int id;
        private String fullName;
        private String email;
        private String password;
        private String role;
        private java.sql.Timestamp createdAt;
        
        // Getters and Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        
        public String getFullName() { return fullName; }
        public void setFullName(String fullName) { this.fullName = fullName; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
        
        public java.sql.Timestamp getCreatedAt() { return createdAt; }
        public void setCreatedAt(java.sql.Timestamp createdAt) { this.createdAt = createdAt; }
    }
}
