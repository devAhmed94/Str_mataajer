package com.app.mataajer.presentation.splash

import androidx.appcompat.app.AppCompatActivity
import com.app.mataajer.presentation.home.HomeActivity
import org.jetbrains.anko.intentFor

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */
class SplashActivity : AppCompatActivity() {

  override fun onStart() {
    super.onStart()
    navigate()
  }

  override fun onBackPressed() {}

  private fun navigate() {
    /*if (showLanguage) startActivity(intentFor<LanguageActivity>())
    else startActivity(intentFor<AdsActivity>())*/
    startActivity(intentFor<HomeActivity>())
  }
}