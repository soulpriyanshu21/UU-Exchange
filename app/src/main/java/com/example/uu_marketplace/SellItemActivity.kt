package com.example.uu_marketplace

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SellItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_item)

        val etTitle = findViewById<EditText>(R.id.etItemTitle)
        val etPrice = findViewById<EditText>(R.id.etItemPrice)
        val etDesc = findViewById<EditText>(R.id.etItemDesc)
        val btnPost = findViewById<Button>(R.id.btnPost)

        btnPost.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val price = etPrice.text.toString().trim()
            val desc = etDesc.text.toString().trim()

            if (title.isEmpty()) { etTitle.error = "Enter item name"; return@setOnClickListener }
            if (price.isEmpty()) { etPrice.error = "Enter price"; return@setOnClickListener }
            if (desc.isEmpty()) { etDesc.error = "Enter description"; return@setOnClickListener }

            Toast.makeText(this, "Posted: $title", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}