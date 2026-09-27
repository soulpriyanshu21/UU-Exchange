package com.example.uu_marketplace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvErp = findViewById<TextView>(R.id.tvErp)
        val tvInfo = findViewById<TextView>(R.id.tvProfileInfo)
        val btnEdit = findViewById<Button>(R.id.btnEditProfile)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        val erpId = SecurePreferences.getErpId(this)
        val name = SecurePreferences.getName(this).ifBlank { "Student" }

        tvName.text = name
        tvErp.text = "ERP: $erpId"

        val course = SecurePreferences.getCourse(this).ifBlank { "Not set" }
        val year = SecurePreferences.getYear(this).ifBlank { "Not set" }
        val building = SecurePreferences.getBuilding(this).ifBlank { "Not set" }
        val hostel = SecurePreferences.getHostel(this).ifBlank { "Not set" }
        val phone = SecurePreferences.getPhone(this).ifBlank { "Not set" }
        val email = SecurePreferences.getEmail(this).ifBlank { "Not set" }

        tvInfo.text = "Name: $name\n" +
                "ERP ID: $erpId\n" +
                "Course: $course\n" +
                "Year: $year\n" +
                "Building: $building\n" +
                "Hostel/PG: $hostel\n" +
                "Phone: $phone\n" +
                "Email: $email"

        btnEdit.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

        btnLogout.setOnClickListener {
            SecurePreferences.clearPreferences(this)
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh displayed data WITHOUT recreating the activity (avoids blinking)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvErp = findViewById<TextView>(R.id.tvErp)
        val tvInfo = findViewById<TextView>(R.id.tvProfileInfo)

        val erpId = SecurePreferences.getErpId(this)
        val name = SecurePreferences.getName(this).ifBlank { "Student" }

        tvName.text = name
        tvErp.text = "ERP: $erpId"
        tvInfo.text = "Name: $name\n" +
                "ERP ID: $erpId\n" +
                "Course: ${SecurePreferences.getCourse(this).ifBlank { "Not set" }}\n" +
                "Year: ${SecurePreferences.getYear(this).ifBlank { "Not set" }}\n" +
                "Building: ${SecurePreferences.getBuilding(this).ifBlank { "Not set" }}\n" +
                "Hostel/PG: ${SecurePreferences.getHostel(this).ifBlank { "Not set" }}\n" +
                "Phone: ${SecurePreferences.getPhone(this).ifBlank { "Not set" }}\n" +
                "Email: ${SecurePreferences.getEmail(this).ifBlank { "Not set" }}"
    }
}