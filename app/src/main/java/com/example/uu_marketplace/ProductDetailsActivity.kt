package com.example.uu_marketplace

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val title = intent.getStringExtra("title") ?: "Item"
        val price = intent.getStringExtra("price") ?: "₹0"
        val desc  = intent.getStringExtra("description") ?: ""
        val imageRes = intent.getIntExtra("imageRes", R.mipmap.ic_launcher)

        val imgProduct = findViewById<ImageView>(R.id.imgDetailProduct)
        val tvTitle = findViewById<TextView>(R.id.tvDetailTitle)
        val tvPrice = findViewById<TextView>(R.id.tvDetailPrice)
        val tvDesc  = findViewById<TextView>(R.id.tvDetailDesc)
        val tvSeller = findViewById<TextView>(R.id.tvSeller)
        val btnContact = findViewById<Button>(R.id.btnContactSeller)
        val btnSpeak = findViewById<Button>(R.id.btnSpeak)

        imgProduct.setImageResource(imageRes)
        tvTitle.text = title
        tvPrice.text = price
        tvDesc.text = desc

        val erpId = SecurePreferences.getErpId(this)
        tvSeller.text = "Seller ERP: $erpId"

        btnContact.setOnClickListener {
            val message = "Hi, I'm interested in your listing: $title ($price)"
            try {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("smsto:$erpId")
                    putExtra("sms_body", message)
                }
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "No SMS app found", Toast.LENGTH_SHORT).show()
            }
        }

        btnSpeak.setOnClickListener {
            Toast.makeText(this, "TTS coming soon", Toast.LENGTH_SHORT).show()
        }
    }
}