package com.app.mataajer.data.application

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.app.mataajer.presentation.exception.ExceptionActivity
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import kotlin.system.exitProcess

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */
class App : Application() {

  companion object {

    lateinit var instance: App
      private set
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
    setUncaughtExceptionHandler()
  }

  private fun setUncaughtExceptionHandler() {
    Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
      FirebaseCrashlytics.getInstance().recordException(throwable)
      startActivity(intentFor<ExceptionActivity>().clearTask().newTask())
      exitProcess(1)
    }
  }
}