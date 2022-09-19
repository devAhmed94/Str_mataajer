package com.app.mataajer.application.app

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StringRes
import com.app.mataajer.application.app.App
import com.app.mataajer.application.utils.LocaleHelper.locale
import java.util.*

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */

const val REQUEST_FLEXIBLE_UPDATE = 100
const val YOUR_APP_CENTER = "b83b60c2-557e-4f1e-8eca-fcf2780da747"
const val DATA_BASE_NAME ="mataajer_database"

fun getContext(): Context {
  return App.instance.applicationContext
}

fun getStringByLocal(id: Int): String {
  val configuration = Configuration(getContext().resources.configuration)
  configuration.setLocale(Locale(locale))
  return getContext().createConfigurationContext(configuration).resources.getString(id)
}

fun getStringByLocal(@StringRes resId: Int, vararg formatArgs: Any): String {
  val configuration = Configuration(getContext().resources.configuration)
  configuration.setLocale(Locale(locale))
  return getContext().createConfigurationContext(configuration).resources.getString(resId, *formatArgs)
}