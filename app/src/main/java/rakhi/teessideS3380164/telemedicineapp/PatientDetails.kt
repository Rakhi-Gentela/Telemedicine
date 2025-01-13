package rakhi.teessideS3380164.telemedicineapp

import android.content.Context

object PatientDetails {

    fun savePatientLoginStatus(currentContext: Context, value: Boolean) {
        val patientLoginStatus = currentContext.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        val statuseditor = patientLoginStatus.edit()
        statuseditor.putBoolean("PATIENTLOGIN_STATUS", value).apply()
    }

    fun getPatientLoginStatus(currentContext: Context): Boolean {
        val patientLoginStatus = currentContext.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        return patientLoginStatus.getBoolean("PATIENTLOGIN_STATUS", false)
    }

    fun savePatientName(currentContext: Context, name: String) {
        val patientName = currentContext.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        val nameeditor = patientName.edit()
        nameeditor.putString("PATIENT_NAME", name).apply()
    }

    fun getPatientName(currentContext: Context): String? {
        val patientName = currentContext.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        return patientName.getString("PATIENT_NAME", null)
    }

    fun savePatientAge(currentContext: Context, name: String) {
        val patientAge = currentContext.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        val ageeditor = patientAge.edit()
        ageeditor.putString("PATIENT_AGE", name).apply()
    }

    fun getPatientAge(currentContext: Context): String? {
        val patientAge = currentContext.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        return patientAge.getString("PATIENT_AGE", null)
    }

    fun savePatientEmail(currentContext: Context, email: String) {
        val patientEmail = currentContext.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        val emaileditor = patientEmail.edit()
        emaileditor.putString("PATIENT_EMAIL", email).apply()
    }

    fun getPatientEmail(currentContext: Context): String? {
        val patientEmail = currentContext.getSharedPreferences("TeleMedicine", Context.MODE_PRIVATE)
        return patientEmail.getString("PATIENT_EMAIL", null)
    }

}

