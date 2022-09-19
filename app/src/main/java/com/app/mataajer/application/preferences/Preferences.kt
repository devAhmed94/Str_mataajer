package com.app.mataajer.application.preferences

import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.app.mataajer.application.app.getContext

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */

private const val FCM_TOKEN = "FcmToken"
private const val SHOW_NOTIFICATIONS = "ShowPushNotifications"
private const val  DARK_MODE = "DarkMode"

private val pref by lazy { getDefaultSharedPreferences(getContext()) }

var fcmToken: String
  get() = pref.getString(FCM_TOKEN, "") ?: ""
  set(value) = pref.edit().putString(FCM_TOKEN, value).apply()

var notificationsAllowed: Boolean
  get() = pref.getBoolean(SHOW_NOTIFICATIONS, true)
  set(value) = pref.edit().putBoolean(SHOW_NOTIFICATIONS, value).apply()

var darkMode: Boolean
  get() = pref.getBoolean(DARK_MODE, false)
  set(value) = pref.edit().putBoolean(DARK_MODE, value).apply()


/*var user: User?
  get() = Gson().fromJson(pref.getString(USER, ""), User::class.java)
  set(value) = pref.edit().putString(USER, Gson().toJson(value)).apply()

fun isUser(): Boolean = user != null

fun removeUser() = remove(USER)*/

private fun contains(key: String): Boolean = pref.contains(key)

private fun remove(vararg keys: String) {
  for (key in keys) {
    pref.edit().remove(key).apply()
  }
}

fun clear() {
  pref.edit().clear().apply()
}

fun registerOnPrefChangeListener(listener: OnSharedPreferenceChangeListener) {
  pref.registerOnSharedPreferenceChangeListener(listener)
}

fun unregisterOnPrefChangeListener(listener: OnSharedPreferenceChangeListener) {
  pref.unregisterOnSharedPreferenceChangeListener(listener)
}