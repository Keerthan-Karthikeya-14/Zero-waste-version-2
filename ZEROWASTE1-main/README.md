# ZeroWaste - AI Powered Food Donation System

## Project Domain
Web Development

## Problem Statement and Use Case
Develop a scalable, intelligent web platform that minimizes food wastage by connecting food donors (households, restaurants, caterers, and canteens) with verified recipients (NGOs, shelters, and individuals in need) using real-time geolocation, AI-driven insights, and automated logistics management.

## Project Structure

```
ZEROWASTE1-main/
├── frontend/
│   ├── admin/
│   │   ├── pages/
│   │   │   ├── admin.html
│   │   │   ├── Admin_dashboard.html
│   │   │   └── admin-login.html
│   │   ├── css/
│   │   │   ├── Admin_dashboard.css
│   │   │   └── admin-login.css
│   │   ├── js/
│   │   │   └── admin-login.js
│   │   └── assets/
│   │       ├── Admin_dashboard.png
│   │       ├── Admin_login.png
│   │       ├── Dashboard1.png
│   │       └── Dashboard2.png
│   │
│   ├── donor/
│   │   ├── pages/
│   │   │   ├── donor_dashboard.html
│   │   │   ├── donor-dashboard.html
│   │   │   └── donor-login.html
│   │   ├── css/
│   │   │   ├── donor-dashboard.css
│   │   │   └── donor-login.css
│   │   ├── js/
│   │   │   ├── donor-dashboard.js
│   │   │   └── donor-login.js
│   │   └── assets/
│   │       ├── Donor_login.png
│   │       └── Donation_Details.png
│   │
│   ├── receiver/
│   │   ├── pages/
│   │   │   ├── receiver_dashboard.html
│   │   │   └── receiver_dashboad.html
│   │   ├── css/
│   │   │   └── receiver_dashboard.css
│   │   └── js/
│   │       └── receiver-dashboard.js
│   │
│   └── shared/
│       ├── pages/
│       │   ├── index.html
│       │   ├── map-dashboard.html
│       │   └── registration.html
│       ├── css/
│       │   ├── auth.css
│       │   ├── main.css
│       │   ├── map-dashboard.css
│       │   └── styles.css
│       ├── js/
│       │   └── map-dashboard.js
│       └── assets/
│           ├── GEOlocation of food Donations.png
│           ├── img1.jpg
│           ├── img2.jpg
│           ├── logo.jpg
│           ├── MAIN 1.jpg
│           ├── main 2.jpg
│           ├── main 3.jpg
│           ├── main 4.jpg
│           ├── Recevier feedback and ratings.png
│           ├── Recevier request.png
│           └── Recevier.png
│
├── backend/
│   ├── api/
│   │   ├── auth.php
│   │   ├── base.php
│   │   ├── donations.php
│   │   ├── get_donations.java
│   │   └── requests.php
│   ├── auth/
│   │   ├── auth.js
│   │   ├── auth-routes.js
│   │   └── auth-middleware.js
│   ├── config/
│   │   └── database.php
│   ├── admin.html
│   ├── database.sql
│   ├── package.json
│   └── static-server.js
│
├── database/
│   └── database.sql
│
└── desktop.ini (system file)
```

## Folder Structure Explanation

### Frontend
- **admin/**: Admin panel interface for system management
- **donor/**: Donor dashboard for posting and managing food donations
- **receiver/**: Receiver interface for finding and requesting food donations
- **shared/**: Common components, assets, and shared pages

### Backend
- **api/**: REST API endpoints (Java files)
- **auth/**: Authentication and authorization modules
- **config/**: Database and configuration files

### Database
- **database/**: SQL schema and database files

## Key Features
- Real-time geolocation-based food matching
- Multi-role authentication (Admin, Donor, Receiver)
- AI-powered insights and recommendations
- Automated logistics management
- Responsive web design

## Technologies Used
- Frontend: HTML5, CSS3, JavaScript, Bootstrap
- Backend: java , servelets, jdbc,
- Database: MySQL
- Maps: Leaflet, Google Maps API
- Authentication: jdbc,servelets

# Abstract / Problem Description 

    To create a web-based platform that facilitates the donation of surplus food to those in need by connecting donors, receivers, and volunteers in real-time
    CORE USERS ROLES :
    -> Donors : Individuals or restaurants with surplus food 
    ->Receivers : NGOs, shelters, and other organizations that distribute food to those in need
    ->volunteers:  Individuals who assist with food transportation 
    ->Administrators: System managers who moderate content and users
# Teck Stack
     Frontend : HTML,CSS,JS
     Frontend Framework : REACT.JS,TAILWIND CSS,BOOTSTRAP(4.5v)
     Backend : NODE.JS,PHP,JS
     Databases : postgreSQL
     GEOlocation & Maps: Leaflet.js and openStreetMap

 #project Explination 

    The technical specifications for developing a food donation platform that connects food donors (individuals and restaurants) with receivers (NGOs and shelters) through a network of volunteers. The application aims to reduce food waste and address food insecurity through an efficient digital matching system with geolocation capabilities
    1. Dashboard 
    it contain donor panel,reciver panel, GEO map Dashboard, admin panel  when we click on it then it open its own  panels accordingly with respective logins.
    Food importance
    Global food waste crisis
    Donations at work 
    Donation review 
    Footer side(about us , links, contact us)
    Follow us section
    2.Donor panel
    User profile management
    Food donation form:
      •	Food type categorization
      •	Quantity input (manual or AI-assisted)
      •	Expiration date selection
      •	Image upload capability 
      •	Location detection and confirmation
    Donation history and tracking 
    Status monitoring (Pending → Accepted → Picked → Verified) 
    3.Receiver panel
    Organization profile management 
    Food availability dashboard
    Advanced filtering options: 
       . Veg/non-veg
       . All freshness (Perishable/non-perishable)
       .Distance based
       .Quantity ranges 
    Request processing (Available Donations)
    Pickup coordination 
    Donation receipt confirmation 
    Feedback system for donors and volunteers
    4.GeoMap Dashboard 
    Interactive map integration 
    Color-coded marker system:
        Green: Fresh food available
        Yellow: Approaching expiration 
        Red: Expired or already claimed
        blue: our location
    5.Admin panel
    User Management 
       Active status,roles(donors),Active time , no of donations.
    Overview 
       Food saved , people served , expired posts , no of active NGO’S(companies)
    food posts
      Quantity,expiry date, status (climed/not)
    NGO Management
         Companies ,location status(active/not)
#project Screenshorts
![image alt](https://github.com/Keerthan-Karthikeya-14/ZEROWASTE1/blob/129d7f60209a3759655d888c0106981183c25e16/Dashboard1.png)
![image alt](https://github.com/Keerthan-Karthikeya-14/ZEROWASTE1/blob/bb1e3e4062dfd8ea6a8b9f0ad89cc551b1ee0c23/Dashboard2.png)
![image alt](https://github.com/Keerthan-Karthikeya-14/ZEROWASTE1/blob/bb1e3e4062dfd8ea6a8b9f0ad89cc551b1ee0c23/Donor%20login.png)
![image alt](https://github.com/Keerthan-Karthikeya-14/ZEROWASTE1/blob/bb1e3e4062dfd8ea6a8b9f0ad89cc551b1ee0c23/Donation%20Details.png)
![image alt](https://github.com/Keerthan-Karthikeya-14/ZEROWASTE1/blob/bb1e3e4062dfd8ea6a8b9f0ad89cc551b1ee0c23/Recevier.png)
![image alt](https://github.com/Keerthan-Karthikeya-14/ZEROWASTE1/blob/bb1e3e4062dfd8ea6a8b9f0ad89cc551b1ee0c23/Recevier%20request.png)
![image alt](https://github.com/Keerthan-Karthikeya-14/ZEROWASTE1/blob/bb1e3e4062dfd8ea6a8b9f0ad89cc551b1ee0c23/Recevier%20feedback%20and%20ratings.png)
![image alt](https://github.com/Keerthan-Karthikeya-14/ZEROWASTE1/blob/bb1e3e4062dfd8ea6a8b9f0ad89cc551b1ee0c23/GEOlocation%20of%20food%20Donations.png)
![image alt](https://github.com/Keerthan-Karthikeya-14/ZEROWASTE1/blob/bb1e3e4062dfd8ea6a8b9f0ad89cc551b1ee0c23/Admin%20login.png)
![image alt](https://github.com/Keerthan-Karthikeya-14/ZEROWASTE1/blob/bb1e3e4062dfd8ea6a8b9f0ad89cc551b1ee0c23/Admin%20dashboard.png)





    

    





















 
     
