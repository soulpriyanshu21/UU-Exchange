package com.example.uu_marketplace

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object SecurePreferences {

    private const val PREF_NAME = "uu_marketplace_secure_prefs"
    private const val KEY_ERP_ID = "student_erp_id"
    private const val KEY_NAME = "student_name"
    private const val KEY_PHONE = "student_phone"
    private const val KEY_EMAIL = "student_email"
    private const val KEY_COURSE = "student_course"
    private const val KEY_YEAR = "student_year"
    private const val KEY_BUILDING = "student_building"
    private const val KEY_HOSTEL = "student_hostel"

    private fun getEncryptedPrefs(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    // ERP
    fun saveErpId(c: Context, v: String) = getEncryptedPrefs(c).edit().putString(KEY_ERP_ID, v).apply()
    fun getErpId(c: Context) = getEncryptedPrefs(c).getString(KEY_ERP_ID, "Unknown") ?: "Unknown"

    // Name
    fun saveName(c: Context, v: String) = getEncryptedPrefs(c).edit().putString(KEY_NAME, v).apply()
    fun getName(c: Context) = getEncryptedPrefs(c).getString(KEY_NAME, "") ?: ""

    // Phone
    fun savePhone(c: Context, v: String) = getEncryptedPrefs(c).edit().putString(KEY_PHONE, v).apply()
    fun getPhone(c: Context) = getEncryptedPrefs(c).getString(KEY_PHONE, "") ?: ""

    // Email
    fun saveEmail(c: Context, v: String) = getEncryptedPrefs(c).edit().putString(KEY_EMAIL, v).apply()
    fun getEmail(c: Context) = getEncryptedPrefs(c).getString(KEY_EMAIL, "") ?: ""

    // Course
    fun saveCourse(c: Context, v: String) = getEncryptedPrefs(c).edit().putString(KEY_COURSE, v).apply()
    fun getCourse(c: Context) = getEncryptedPrefs(c).getString(KEY_COURSE, "") ?: ""

    // Year
    fun saveYear(c: Context, v: String) = getEncryptedPrefs(c).edit().putString(KEY_YEAR, v).apply()
    fun getYear(c: Context) = getEncryptedPrefs(c).getString(KEY_YEAR, "") ?: ""

    // Building
    fun saveBuilding(c: Context, v: String) = getEncryptedPrefs(c).edit().putString(KEY_BUILDING, v).apply()
    fun getBuilding(c: Context) = getEncryptedPrefs(c).getString(KEY_BUILDING, "") ?: ""

    // Hostel
    fun saveHostel(c: Context, v: String) = getEncryptedPrefs(c).edit().putString(KEY_HOSTEL, v).apply()
    fun getHostel(c: Context) = getEncryptedPrefs(c).getString(KEY_HOSTEL, "") ?: ""

    // Clear
    fun clearPreferences(c: Context) = getEncryptedPrefs(c).edit().clear().apply()
}