package com.zerowaste.servlet;

import com.zerowaste.dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Register Servlet
 * Handles user registration requests
 * @author Zero Waste Team
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    
    private UserDAO userDAO;
    
    @Override
    public void init() throws ServletException {
        System.out.println("DEBUG: RegisterServlet initialized");
        userDAO = new UserDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("DEBUG: RegisterServlet POST request received");
        
        // Get form parameters
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String role = request.getParameter("role");
        
        System.out.println("DEBUG: Registration attempt - Full Name: " + fullName + ", Email: " + email + ", Role: " + role);
        
        // Validate input parameters
        String errorMessage = validateRegistrationInput(fullName, email, password, confirmPassword, role);
        
        if (errorMessage != null) {
            System.out.println("ERROR: Registration validation failed - " + errorMessage);
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("fullName", fullName);
            request.setAttribute("email", email);
            request.setAttribute("role", role);
            request.getRequestDispatcher("/shared/pages/registration.html").forward(request, response);
            return;
        }
        
        try {
            // Check if user already exists
            UserDAO.User existingUser = userDAO.findUserByEmail(email.trim());
            
            if (existingUser != null) {
                System.out.println("ERROR: Registration failed - Email already exists: " + email);
                request.setAttribute("errorMessage", "Email address is already registered");
                request.setAttribute("fullName", fullName);
                request.setAttribute("email", email);
                request.setAttribute("role", role);
                request.getRequestDispatcher("/shared/pages/registration.html").forward(request, response);
                return;
            }
            
            // Insert new user
            boolean success = userDAO.insertUser(
                fullName.trim(), 
                email.trim(), 
                password, // In production, this should be hashed
                role.toUpperCase()
            );
            
            if (success) {
                System.out.println("DEBUG: Registration successful for email: " + email);
                
                // Create session for the newly registered user
                HttpSession session = request.getSession();
                session.setAttribute("user", userDAO.findUserByEmail(email.trim()));
                session.setAttribute("isLoggedIn", true);
                session.setAttribute("userRole", role.toUpperCase());
                
                // Redirect based on user role
                String redirectPage = getRedirectPage(role);
                System.out.println("DEBUG: Redirecting new user to: " + redirectPage);
                response.sendRedirect(redirectPage);
            } else {
                System.out.println("ERROR: Registration failed - Database error for email: " + email);
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.setAttribute("fullName", fullName);
                request.setAttribute("email", email);
                request.setAttribute("role", role);
                request.getRequestDispatcher("/shared/pages/registration.html").forward(request, response);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: Exception during registration process - " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred during registration. Please try again.");
            request.setAttribute("fullName", fullName);
            request.setAttribute("email", email);
            request.setAttribute("role", role);
            request.getRequestDispatcher("/shared/pages/registration.html").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("DEBUG: RegisterServlet GET request received - redirecting to registration page");
        response.sendRedirect("/shared/pages/registration.html");
    }
    
    /**
     * Validates registration input parameters
     * @param fullName User's full name
     * @param email User's email
     * @param password User's password
     * @param confirmPassword Password confirmation
     * @param role User's role
     * @return Error message if validation fails, null if successful
     */
    private String validateRegistrationInput(String fullName, String email, String password, String confirmPassword, String role) {
        
        // Check for null or empty fields
        if (fullName == null || fullName.trim().isEmpty()) {
            return "Full name is required";
        }
        
        if (email == null || email.trim().isEmpty()) {
            return "Email address is required";
        }
        
        if (password == null || password.trim().isEmpty()) {
            return "Password is required";
        }
        
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            return "Password confirmation is required";
        }
        
        if (role == null || role.trim().isEmpty()) {
            return "Please select a role";
        }
        
        // Validate email format (simple validation)
        if (!email.contains("@") || !email.contains(".")) {
            return "Please enter a valid email address";
        }
        
        // Validate password length
        if (password.length() < 6) {
            return "Password must be at least 6 characters long";
        }
        
        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            return "Passwords do not match";
        }
        
        // Validate role
        if (!role.equalsIgnoreCase("ADMIN") && !role.equalsIgnoreCase("DONOR") && !role.equalsIgnoreCase("RECEIVER")) {
            return "Invalid role selected";
        }
        
        return null; // Validation successful
    }
    
    /**
     * Determines the appropriate redirect page based on user role
     * @param role User's role
     * @return Redirect page URL
     */
    private String getRedirectPage(String role) {
        switch (role.toUpperCase()) {
            case "ADMIN":
                return "admin/pages/Admin_dashboard.html";
            case "DONOR":
                return "donor/pages/donor-dashboard.html";
            case "RECEIVER":
                return "receiver/pages/receiver_dashboard.html";
            default:
                System.out.println("WARNING: Unknown role - defaulting to donor dashboard: " + role);
                return "donor/pages/donor-dashboard.html";
        }
    }
}
