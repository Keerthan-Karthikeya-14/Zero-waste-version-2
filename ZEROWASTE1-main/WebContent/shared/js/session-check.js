// Session Check JavaScript
// Redirects to login if user is not authenticated

function checkSession() {
    console.log("DEBUG: Checking user session...");
    
    // In a real JSP application, this would be handled server-side
    // For this demo, we'll use a simple client-side check
    // In production, use JSP session checking: <% if (session.getAttribute("isLoggedIn") == null) { %>
    
    // Check if user is logged in (simplified for demo)
    const isLoggedIn = sessionStorage.getItem('isLoggedIn') === 'true';
    
    if (!isLoggedIn) {
        console.log("DEBUG: User not logged in, redirecting to login page");
        alert('Please login to access this page');
        window.location.href = '../shared/pages/index.html';
        return false;
    }
    
    console.log("DEBUG: User session is valid");
    return true;
}

// Logout function
function logout() {
    console.log("DEBUG: Logging out user...");
    
    // Clear session storage
    sessionStorage.removeItem('isLoggedIn');
    sessionStorage.removeItem('userInfo');
    
    // Redirect to login page
    window.location.href = '../shared/pages/index.html';
}

// Run session check when page loads
document.addEventListener('DOMContentLoaded', function() {
    checkSession();
    
    // Add logout event listeners
    const logoutButtons = document.querySelectorAll('.logout-btn, [onclick*="logout"]');
    logoutButtons.forEach(function(button) {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            logout();
        });
    });
});

// For demo purposes - simulate login
function simulateLogin(userRole) {
    sessionStorage.setItem('isLoggedIn', 'true');
    sessionStorage.setItem('userInfo', JSON.stringify({
        role: userRole,
        name: 'Demo User'
    }));
}
