-- Zero Waste Food Donation System Database Schema
-- MySQL Database Schema
-- Created: May 3, 2026

-- Create database if not exists
CREATE DATABASE IF NOT EXISTS zerowaste_db;
USE zerowaste_db;

-- Drop existing tables if they exist (for clean setup)
DROP TABLE IF EXISTS waste_entries;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS categories;

-- 1. Categories Table
-- Stores different types of waste/food categories
CREATE TABLE categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_category_name (category_name)
);

-- 2. Users Table
-- Stores user information and authentication data
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- In production, store hashed passwords
    role ENUM('ADMIN', 'DONOR', 'RECEIVER') NOT NULL DEFAULT 'DONOR',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_email (email),
    INDEX idx_role (role)
);

-- 3. Waste Entries Table
-- Stores waste/food donation entries
CREATE TABLE waste_entries (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    waste_type VARCHAR(100) NOT NULL,
    quantity_kg DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    entry_date DATE NOT NULL,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id),
    INDEX idx_entry_date (entry_date),
    INDEX idx_waste_type (waste_type)
);

-- Insert sample data into categories table
INSERT INTO categories (category_name, description) VALUES
('Vegetables', 'Fresh vegetables and vegetable products'),
('Fruits', 'Fresh fruits and fruit products'),
('Grains', 'Rice, wheat, and other grain products'),
('Dairy', 'Milk, cheese, and dairy products'),
('Meat', 'Meat and poultry products'),
('Bakery', 'Bread, cakes, and bakery items'),
('Cooked Food', 'Prepared and cooked meals'),
('Beverages', 'Liquid food items and beverages'),
('Other', 'Miscellaneous food items');

-- Insert sample admin user (password: admin123)
INSERT INTO users (full_name, email, password, role) VALUES
('System Administrator', 'admin@zerowaste.com', 'admin123', 'ADMIN'),
('John Donor', 'donor@zerowaste.com', 'donor123', 'DONOR'),
('Sarah Receiver', 'receiver@zerowaste.com', 'receiver123', 'RECEIVER');

-- Insert sample waste entries
INSERT INTO waste_entries (user_id, waste_type, quantity_kg, entry_date, notes) VALUES
(2, 'Vegetables', 5.50, '2026-05-03', 'Fresh vegetables from restaurant'),
(2, 'Bakery', 3.25, '2026-05-02', 'Day-old bread and pastries'),
(3, 'Fruits', 2.75, '2026-05-01', 'Seasonal fruits collected from market');

-- Display success message
SELECT 'Zero Waste Database Schema Created Successfully!' as status;
SELECT COUNT(*) as categories_count FROM categories;
SELECT COUNT(*) as users_count FROM users;
SELECT COUNT(*) as waste_entries_count FROM waste_entries;
