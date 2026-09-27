package com.example.uu_marketplace

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val etName = findViewById<EditText>(R.id.etName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etCourse = findViewById<EditText>(R.id.etCourse)
        val etYear = findViewById<EditText>(R.id.etYear)
        val etBuilding = findViewById<EditText>(R.id.etBuilding)
        val etHostel = findViewById<EditText>(R.id.etHostel)
        val btnSave = findViewById<Button>(R.id.btnSaveProfile)

        // Pre-fill with existing data
        etName.setText(SecurePreferences.getName(this))
        etPhone.setText(SecurePreferences.getPhone(this))
        etEmail.setText(SecurePreferences.getEmail(this))
        etCourse.setText(SecurePreferences.getCourse(this))
        etYear.setText(SecurePreferences.getYear(this))
        etBuilding.setText(SecurePreferences.getBuilding(this))
        etHostel.setText(SecurePreferences.getHostel(this))

        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val course = etCourse.text.toString().trim()
            val year = etYear.text.toString().trim()
            val building = etBuilding.text.toString().trim()
            val hostel = etHostel.text.toString().trim()

            // Validation
            if (name.isEmpty()) {
                etName.error = "Name is required"
                return@setOnClickListener
            }
            if (phone.isNotEmpty() && (phone.length != 10 || !phone.all { it.isDigit() })) {
                etPhone.error = "Phone must be 10 digits"
                return@setOnClickListener
            }
            if (email.isNotEmpty() && !email.contains("@")) {
                etEmail.error = "Enter valid email"
                return@setOnClickListener
            }

            // Save
            SecurePreferences.saveName(this, name)
            SecurePreferences.savePhone(this, phone)
            SecurePreferences.saveEmail(this, email)
            SecurePreferences.saveCourse(this, course)
            SecurePreferences.saveYear(this, year)
            SecurePreferences.saveBuilding(this, building)
            SecurePreferences.saveHostel(this, hostel)

            Toast.makeText(this, "Profile updated!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}