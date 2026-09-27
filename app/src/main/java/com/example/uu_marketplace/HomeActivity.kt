package com.example.uu_marketplace

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_dashboard)

        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        val tvErp = findViewById<TextView>(R.id.tvErp)
        val rvProducts = findViewById<RecyclerView>(R.id.rvProducts)
        val fabSell = findViewById<FloatingActionButton>(R.id.fabSell)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        val erpId = SecurePreferences.getErpId(this)
        val name = SecurePreferences.getName(this)

        // Dynamic greeting
        tvGreeting.text = if (name.isNotBlank()) "Welcome, $name 👋" else "Welcome, Student 👋"
        tvErp.text = "ERP: $erpId"

        rvProducts.layoutManager = GridLayoutManager(this, 2)
        rvProducts.adapter = ProductAdapter(getElectronicsItems()) { product ->
            val intent = Intent(this, ProductDetailsActivity::class.java)
            intent.putExtra("title", product.title)
            intent.putExtra("price", product.price)
            intent.putExtra("description", product.description)
            intent.putExtra("imageRes", product.imageRes)
            startActivity(intent)
        }

        fabSell.setOnClickListener {
            startActivity(Intent(this, SellItemActivity::class.java))
        }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_search -> {
                    startActivity(Intent(this, SearchActivity::class.java)); true
                }
                R.id.nav_saved -> {
                    startActivity(Intent(this, SavedItemsActivity::class.java)); true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java)); true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh greeting when returning from Edit Profile
        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        val name = SecurePreferences.getName(this)
        tvGreeting.text = if (name.isNotBlank()) "Welcome, $name 👋" else "Welcome, Student 👋"
    }

    private fun getElectronicsItems(): List<Product> = listOf(
        Product("iPhone 11 (64GB)", "₹18,000", "Good condition, 89% battery", R.drawable.iphone11),
        Product("Dell Inspiron 15", "₹22,500", "i5, 8GB RAM, 512GB SSD", R.drawable.dell_laptop),
        Product("Sony WH-1000XM4", "₹12,000", "Noise cancelling, with case", R.drawable.sony_headphones),
        Product("Casio FX-991EX", "₹800", "Semester 3, like new", R.drawable.casio_calculator),
        Product("Logitech Mouse", "₹500", "Wireless, barely used", R.drawable.logitech_mouse),
        Product("Boat Earbuds", "₹1,200", "Airdopes 141, 6 months old", R.drawable.boat_earbuds),
        Product("Kindle Paperwhite", "₹6,500", "10th gen, with cover", R.drawable.kindle),
        Product("Mi Power Bank 20000mAh", "₹1,100", "18W fast charge", R.drawable.powerbank)
    )
}