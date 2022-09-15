package com.app.mataajer.data.application

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StringRes
import com.app.mataajer.data.utils.LocaleHelper.locale
import java.util.*

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */

const val REQUEST_FLEXIBLE_UPDATE = 100

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