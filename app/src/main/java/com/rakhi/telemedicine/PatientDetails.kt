package com.rakhi.telemedicine

import android.content.Context

object PatientDetails {

    private const val PREFS_NAME = "TeleMedicine"

    fun savePatientLoginStatus(context: Context, value: Boolean) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("LOGIN_STATUS", value).apply()
    }

    fun getPatientLoginStatus(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean("LOGIN_STATUS", false)
    }

}