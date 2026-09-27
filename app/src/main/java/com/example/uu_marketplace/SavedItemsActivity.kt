package com.example.uu_marketplace

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SavedItemsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_items)

        val rvSaved = findViewById<RecyclerView>(R.id.rvSaved)
        val tvEmpty = findViewById<TextView>(R.id.tvEmpty)

        val saved = listOf(
            Product("Physics Notes", "₹100", "Semester 2 notes", R.drawable.kindle),
            Product("Casio Watch", "₹800", "Barely used", R.drawable.casio_calculator),
            Product("iPhone Charger", "₹300", "Original Apple", R.drawable.iphone11)
        )

        if (saved.isEmpty()) {
            tvEmpty.visibility = TextView.VISIBLE
            rvSaved.visibility = TextView.GONE
        } else {
            tvEmpty.visibility = TextView.GONE
            rvSaved.visibility = TextView.VISIBLE
            rvSaved.layoutManager = LinearLayoutManager(this)
            rvSaved.adapter = ProductAdapter(saved)
        }
    }
}