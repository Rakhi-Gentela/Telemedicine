package com.telemedicine.S3380164rakhi

import android.content.Context

object PatientDetails {

    fun savePatientLoginStatus(context: Context, value: Boolean) {
        val patientLoginStatus = context.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        val statuseditor = patientLoginStatus.edit()
        statuseditor.putBoolean("PATIENTLOGIN_STATUS", value).apply()
    }

    fun getPatientLoginStatus(context: Context): Boolean {
        val patientLoginStatus = context.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        return patientLoginStatus.getBoolean("PATIENTLOGIN_STATUS", false)
    }

    fun savePatientName(context: Context, name: String) {
        val patientName = context.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        val nameeditor = patientName.edit()
        nameeditor.putString("PATIENT_NAME", name).apply()
    }

    fun getPatientName(context: Context): String? {
        val patientName = context.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        return patientName.getString("PATIENT_NAME", null)
    }

    fun savePatientAge(context: Context, name: String) {
        val patientAge = context.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        val ageeditor = patientAge.edit()
        ageeditor.putString("PATIENT_AGE", name).apply()
    }

    fun getPatientAge(context: Context): String? {
        val patientAge = context.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        return patientAge.getString("PATIENT_AGE", null)
    }

    fun savePatientEmail(context: Context, email: String) {
        val patientEmail = context.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        val emaileditor = patientEmail.edit()
        emaileditor.putString("PATIENT_EMAIL", email).apply()
    }

    fun getPatientEmail(context: Context): String? {
        val patientEmail = context.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        return patientEmail.getString("PATIENT_EMAIL", null)
    }

}

