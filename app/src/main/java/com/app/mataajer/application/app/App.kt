package com.app.mataajer.application.app

import android.app.Application
import com.app.mataajer.R
import com.app.mataajer.application.di.databaseModule
import com.app.mataajer.application.di.repositoryModule
import com.app.mataajer.application.di.serviceApiModule
import com.app.mataajer.application.di.viewModelModule
import com.app.mataajer.application.preferences.darkMode
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.app.mataajer.presentation.exception.ExceptionActivity
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
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
        settingKoin()
        setUncaughtExceptionHandler()
        settingAppCenter()
    }


    private fun settingKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    serviceApiModule,
                    databaseModule
                )
            )
        }
    }

    private fun setUncaughtExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            FirebaseCrashlytics.getInstance().recordException(throwable)
            startActivity(intentFor<ExceptionActivity>().clearTask().newTask())
            exitProcess(1)
        }
    }

    private fun settingAppCenter() {
        AppCenter.configure(instance, YOUR_APP_CENTER)
        if (AppCenter.isConfigured()) {
            AppCenter.start(Analytics::class.java)
            AppCenter.start(Crashes::class.java)
        }

        if (!AppCenter.isConfigured()) {
            AppCenter.start(instance, YOUR_APP_CENTER, Crashes::class.java);
        }

        Crashes.setEnabled(true)
        Crashes.hasReceivedMemoryWarningInLastSession()
        Crashes.getLastSessionCrashReport()
        Crashes.hasCrashedInLastSession()

        /* try {
             throw Exception()
         } catch (exception: java.lang.Exception) {
             Crashes.trackError(exception)
         }*/
    }
}