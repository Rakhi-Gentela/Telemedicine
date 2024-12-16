package com.rakhiS3380164.telemedicine

import android.app.Activity
import android.content.Intent

object Navigation {

    fun goAccessActivity(activity: Activity, kill: Boolean) {
        activity.startActivity(Intent(activity, AccessActivity::class.java))
        if (kill)
            activity.finish()
    }
}