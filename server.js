const express = require('express');
const mysql = require('mysql2');
const cors = require('cors');
require('dotenv').config();

const app = express();
app.use(express.json());
app.use(cors());

// Configure MySQL Database Connection
const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',          // Change to your MySQL username
    password: '',      // Change to your MySQL password
    database: 'college_marketplace'
});

db.connect((err) => {
    if (err) {
        console.error('Database connection failed: ' + err.stack);
        return;
    }
    console.log('Connected to MySQL database successfully! 🚀');
});

// --- API ENDPOINTS ---

// 1. Get all products (or filter by category like earbuds, laptops)
app.get('/api/products', (req, res) => {
    const category = req.query.category;
    let query = 'SELECT * FROM products WHERE status = "available"';
    let params = [];

    if (category) {
        query += ' AND category = ?';
        params.push(category);
    }

    db.query(query, params, (err, results) => {
        if (err) {
            return res.status(500).json({ error: err.message });
        }
        res.json(results);
    });
});

// 2. Mock ERP Login Endpoint
app.post('/api/login', (req, res) => {
    const { erp_id, password } = req.body;

    // Here you would normally verify against your ERP system or users table
    const query = 'SELECT * FROM users WHERE erp_id = ?';
    db.query(query, [erp_id], (err, results) => {
        if (err) return res.status(500).json({ error: err.message });

        if (results.length === 0) {
            return res.status(401).json({ success: false, message: 'Invalid ERP ID or password' });
        }

        // In a real app, compare hashed passwords here
        res.json({ success: true, message: 'Login successful!', user: results[0] });
    });
});

// Start the server
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});