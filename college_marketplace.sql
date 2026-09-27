-- Step 1: Create the database
CREATE DATABASE IF NOT EXISTS college_marketplace;
USE college_marketplace;

-- Step 2: Create the Users table (for ERP verification)
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    erp_id VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL, -- Stores the securely hashed student password
    full_name VARCHAR(100) NOT NULL,
    college_branch VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Step 3: Create the Products table (for second-hand tech items)
CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    seller_erp_id VARCHAR(50) NOT NULL,
    title VARCHAR(150) NOT NULL,
    category ENUM('phone', 'laptop', 'earbuds', 'speaker', 'mouse', 'keyboard', 'watch', 'kettle', 'other') NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description TEXT,
    image_url VARCHAR(255),
    status ENUM('available', 'sold') DEFAULT 'available',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (seller_erp_id) REFERENCES users(erp_id) ON DELETE CASCADE
);

-- Step 4: Create a table for saved items / watchlists (Buying side profile activity)
CREATE TABLE IF NOT EXISTS student_watchlist (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_erp_id VARCHAR(50) NOT NULL,
    product_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_erp_id) REFERENCES users(erp_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);