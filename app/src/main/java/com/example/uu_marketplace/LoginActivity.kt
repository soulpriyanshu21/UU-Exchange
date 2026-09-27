package com.example.uu_marketplace

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etErpId: EditText
    private lateinit var etPassword: EditText
    private lateinit var etCaptchaInput: EditText
    private lateinit var tvCaptcha: TextView
    private lateinit var btnRefreshCaptcha: ImageButton
    private lateinit var btnLogin: Button
    private lateinit var tvError: TextView

    private var currentCaptcha: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etErpId = findViewById(R.id.etErpId)
        etPassword = findViewById(R.id.etPassword)
        etCaptchaInput = findViewById(R.id.etCaptchaInput)
        tvCaptcha = findViewById(R.id.tvCaptcha)
        btnRefreshCaptcha = findViewById(R.id.btnRefreshCaptcha)
        btnLogin = findViewById(R.id.btnLogin)
        tvError = findViewById(R.id.tvError)

        if (SecurePreferences.getErpId(this) != "Unknown") {
            goToHome()
            return
        }

        generateCaptcha()
        btnRefreshCaptcha.setOnClickListener { generateCaptcha() }

        etErpId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.length > 10) {
                    etErpId.setText(s.subSequence(0, 10))
                    etErpId.setSelection(10)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        btnLogin.setOnClickListener { validateAndLogin() }
    }

    private fun generateCaptcha() {
        val chars = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789"
        currentCaptcha = (1..5).map { chars.random() }.joinToString("")
        tvCaptcha.text = currentCaptcha

        val colors = listOf("#1E88E5", "#43A047", "#E53935", "#8E24AA", "#F4511E")
        tvCaptcha.setTextColor(Color.parseColor(colors.random()))
    }

    private fun validateAndLogin() {
        val erpId = etErpId.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val captchaInput = etCaptchaInput.text.toString().trim().uppercase()

        if (erpId.length != 10 || !erpId.all { it.isDigit() }) {
            tvError.text = "ERP ID must be exactly 10 digits"
            return
        }

        if (password.length < 8 ||
            !password.any { it.isLetter() } ||
            !password.any { it.isDigit() }) {
            tvError.text = "Password must be 8+ chars with letters & numbers"
            return
        }

        if (captchaInput != currentCaptcha) {
            tvError.text = "CAPTCHA incorrect. Try again."
            generateCaptcha()
            etCaptchaInput.text.clear()
            return
        }

        SecurePreferences.saveErpId(this, erpId)
        Toast.makeText(this, "Welcome, $erpId!", Toast.LENGTH_SHORT).show()
        goToHome()
    }

    private fun goToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}