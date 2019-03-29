package ar.com.wolox.android.example.utils

import android.app.Activity
import android.content.SharedPreferences
import android.support.v4.app.Fragment

/**
 * Util class to store keys to use with [SharedPreferences] or as argument between
 * [Fragment] or [Activity].
 */
object Extras {

    object UserLogin {
        const val USERNAME = "username"

        // Shared preferences
        const val USER_EMAIL = "user_email"
        const val USER_SESSION = "user_session"
    }
}
