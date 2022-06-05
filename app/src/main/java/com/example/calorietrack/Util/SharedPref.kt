package com.example.android_breath.Util

import android.app.Activity
import android.content.SharedPreferences
import java.util.*

class SharedPref(activity: Activity) {
    private val preferences: SharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE)

    val date: String get() {
        val milliDate = preferences.getLong("seconds", 0)


        val calendar = Calendar.getInstance()//calander instance
        calendar.timeInMillis = milliDate

        return ("Last Used at " + calendar.get(Calendar.HOUR_OF_DAY) +
                ": " + calendar.get(Calendar.MINUTE) + "  (24 hours Format)")
    }

    fun setDate(milliseconds: Long) {
        preferences.edit().putLong("seconds", milliseconds)
            .apply()
    }

    var sessions: Int
        get() = preferences.getInt("sessions", 0)
        set(session) = preferences.edit().putInt("sessions", session).apply()

}