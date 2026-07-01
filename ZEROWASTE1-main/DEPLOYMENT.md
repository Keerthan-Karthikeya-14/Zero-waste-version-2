# ZeroWaste - Deployment Instructions

## 🚀 Apache Tomcat Deployment Guide

### Prerequisites
- Apache Tomcat 9.0 or higher
- MySQL 8.0 or higher
- JDK 11 or higher
- MySQL Connector JAR

### 📋 Step-by-Step Deployment

#### 1. Database Setup
```bash
# Connect to MySQL
mysql -u root -p

# Execute schema
source /path/to/ZEROWASTE1-main/database/schema.sql
```

#### 2. Project Setup
```bash
# Navigate to project directory
cd ZEROWASTE1-main

# Create WAR file
jar -cvf ZeroWaste.war WebContent/ src/

# Copy MySQL Connector to lib folder
cp mysql-connector-java-8.0.xx.jar lib/
```

#### 3. Tomcat Deployment
```bash
# Deploy to Tomcat
cp ZeroWaste.war $CATALINA_HOME/webapps/

# Start Tomcat
$CATALINA_HOME/bin/startup.sh
```

#### 4. Access Application
- **Login Page**: http://localhost:8080/ZeroWaste/shared/pages/index.html
- **Test Flow**: http://localhost:8080/ZeroWaste/test-flow.html

### 🔐 Test Credentials
- **Admin**: admin@zerowaste.com / admin123
- **Donor**: donor@zerowaste.com / donor123  
- **Receiver**: receiver@zerowaste.com / receiver123

### 🌐 Application URLs
- **Login**: `/shared/pages/index.html`
- **Admin Dashboard**: `/admin/pages/Admin_dashboard.html`
- **Donor Dashboard**: `/donor/pages/donor-dashboard.html`
- **Receiver Dashboard**: `/receiver/pages/receiver_dashboard.html`

### 🐛 Debug Information
All servlets include comprehensive debug logging:
- Check Tomcat logs: `$CATALINA_HOME/logs/catalina.out`
- Browser console for client-side debugging
- Database connection status logged

### ⚙️ Configuration
- **Database URL**: jdbc:mysql://localhost:3306/zerowaste_db
- **Database User**: root (no password)
- **Session Timeout**: 30 minutes
- **Default Port**: 8080

### 🔧 Troubleshooting
1. **Database Connection**: Ensure MySQL is running and schema is imported
2. **ClassPath**: Verify MySQL connector JAR is in lib/ folder
3. **Permissions**: Check Tomcat has read/write permissions
4. **Port Conflicts**: Ensure port 8080 is available
