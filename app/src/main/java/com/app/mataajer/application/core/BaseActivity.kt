package com.app.mataajer.application.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.app.mataajer.R
import com.app.mataajer.application.preferences.darkMode
import com.app.mataajer.application.utils.LocaleHelper
import com.app.mataajer.application.utils.extensions.gone
import com.app.mataajer.application.utils.extensions.hideKeyboard
import com.app.mataajer.application.utils.extensions.load
import com.app.mataajer.application.utils.extensions.visible
import com.app.mataajer.databinding.ActivityBaseBinding
import com.app.mataajer.presentation.loading.LoadingDialog
import java.lang.reflect.ParameterizedType

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

  protected val binding by lazy { initBinding() }
  private val baseBinding by lazy { ActivityBaseBinding.inflate(layoutInflater) }
  private val loadingDialog by lazy { LoadingDialog(this) }

  override fun attachBaseContext(newBase: Context) {
    super.attachBaseContext(LocaleHelper.onAttach(newBase))
  }

  @Suppress("DEPRECATION")
  override fun onCreate(savedInstanceState: Bundle?) {
    if (darkMode) setTheme(R.style.DakModeTheme) else setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    // Set the status bar to dark-semi-transparent
    /*window.setFlags(FLAG_TRANSLUCENT_STATUS, FLAG_TRANSLUCENT_STATUS)*/
    setContent()
  }

  @Suppress("UNCHECKED_CAST")
  private fun initBinding(): VB {
    val superclass = javaClass.genericSuperclass as ParameterizedType
    val method = (superclass.actualTypeArguments[0] as Class<Any>)
        .getDeclaredMethod("inflate", LayoutInflater::class.java)
    return method.invoke(null, layoutInflater) as VB
  }

  private fun setContent() {
    baseBinding.flContainer.addView(binding.root)
    setContentView(baseBinding.root)
  }

  override fun onPause() {
    hideKeyboard(currentFocus)
    super.onPause()
  }

  /**
   * Show loading dialog
   */
  fun showLoading() = loadingDialog.show()

  /**
   * Dismiss loading dialog
   */
  fun dismissLoading() {
    // Make sure that activity is alive otherwise IllegalArgumentException will arise.
    if (isDestroyed.not()) loadingDialog.dismiss()
  }

  /**
   * Show inner loading view
   */
  protected fun showInnerLoading() = with(baseBinding.loadingView) { rlLoading.visible() }

  /**
   * Dismiss inner loading view
   */
  protected fun dismissInnerLoading() = with(baseBinding.loadingView) { rlLoading.gone() }

  /**
   * Show error view
   * @param drawable Image that represent status (Default is connection image)
   * @param message Status message
   * @param showRetry Show or hide retry button (Default is true)
   * @param action Retry button text (Default is Retry)
   * @param onRetry Retry button on click listener
   */
  protected fun showError(@DrawableRes drawable: Int = R.drawable.ic_connection,
                          message: String,
                          showRetry: Boolean = true,
                          action: String? = getString(R.string.base_retry),
                          onRetry: () -> Unit) {
    with(baseBinding.errorView) {
      ivError.load(drawable)
      tvError.text = message
      if (showRetry) {
        btnRetry.visible()
        btnRetry.text = action
        btnRetry.setOnClickListener {
          onRetry.invoke()
          llError.gone()
        }
      }
      llError.visible()
    }
  }
}