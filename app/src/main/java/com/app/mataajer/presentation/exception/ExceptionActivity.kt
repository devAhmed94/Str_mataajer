package com.app.mataajer.presentation.exception

import android.view.View
import com.app.mataajer.core.BaseActivity
import com.app.mataajer.databinding.ActivityExceptionBinding
import com.app.mataajer.presentation.splash.SplashActivity
import org.jetbrains.anko.intentFor

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */
class ExceptionActivity : BaseActivity<ActivityExceptionBinding>() {

  @Suppress("UNUSED_PARAMETER")
  fun onClick(view: View) {
    startActivity(intentFor<SplashActivity>())
    finishAffinity()
  }
}