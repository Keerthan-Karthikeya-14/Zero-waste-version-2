package com.zerowaste.servlet;

import com.zerowaste.dao.UserDAO;
import com.zerowaste.dao.UserDAO.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Login Servlet
 * Handles user authentication and login requests
 * @author Zero Waste Team
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    
    private UserDAO userDAO;
    
    @Override
    public void init() throws ServletException {
        System.out.println("DEBUG: LoginServlet initialized");
        userDAO = new UserDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("DEBUG: LoginServlet POST request received");
        
        // Get form parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        System.out.println("DEBUG: Login attempt - Email: " + email + ", Password: [PROTECTED]");
        
        // Validate input parameters
        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            System.out.println("ERROR: Invalid login parameters - email or password is empty");
            request.setAttribute("errorMessage", "Email and password are required");
            request.getRequestDispatcher("/shared/pages/index.html").forward(request, response);
            return;
        }
        
        try {
            // Find user by email
            User user = userDAO.findUserByEmail(email.trim());
            
            if (user == null) {
                System.out.println("ERROR: Login failed - User not found for email: " + email);
                request.setAttribute("errorMessage", "Invalid email or password");
                request.getRequestDispatcher("/shared/pages/index.html").forward(request, response);
                return;
            }
            
            // Verify password (simple comparison for demo - use hashing in production)
            if (!password.equals(user.getPassword())) {
                System.out.println("ERROR: Login failed - Invalid password for email: " + email);
                request.setAttribute("errorMessage", "Invalid email or password");
                request.getRequestDispatcher("/shared/pages/index.html").forward(request, response);
                return;
            }
            
            // Authentication successful - create session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("userRole", user.getRole());
            
            System.out.println("DEBUG: Login successful - User ID: " + user.getId() + ", Role: " + user.getRole());
            
            // Redirect based on user role
            String redirectPage = getRedirectPage(user.getRole());
            System.out.println("DEBUG: Redirecting to: " + redirectPage);
            
            response.sendRedirect(redirectPage);
            
        } catch (Exception e) {
            System.out.println("ERROR: Exception during login process - " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred during login. Please try again.");
            request.getRequestDispatcher("/shared/pages/index.html").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("DEBUG: LoginServlet GET request received - redirecting to login page");
        response.sendRedirect("/shared/pages/index.html");
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
                System.out.println("WARNING: Unknown role - defaulting to admin dashboard: " + role);
                return "admin/pages/Admin_dashboard.html";
        }
    }
}

