package com.app.mataajer.presentation.loading

import android.app.Activity
import android.graphics.Color.TRANSPARENT
import android.graphics.drawable.ColorDrawable
import android.view.Gravity.CENTER
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.Window.FEATURE_NO_TITLE
import androidx.appcompat.app.AppCompatDialog
import com.race604.drawable.wave.WaveDrawable
import com.app.mataajer.R
import com.app.mataajer.databinding.DialogLoadingBinding

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */
class LoadingDialog(context: Activity) : AppCompatDialog(context, R.style.LoadingTheme) {

  private val binding = DialogLoadingBinding.inflate(layoutInflater)

  init {
    window?.apply {
      setLayout(WRAP_CONTENT, WRAP_CONTENT)
      setBackgroundDrawable(ColorDrawable(TRANSPARENT))
      setGravity(CENTER)
    }
    requestWindowFeature(FEATURE_NO_TITLE)
    setContentView(binding.root)
    setCancelable(false)

    val drawable = WaveDrawable(getContext(), R.drawable.logo_loading)
    binding.ivLoading.setImageDrawable(drawable)
    drawable.isIndeterminate = true
    drawable.setWaveAmplitude(30)
    drawable.setWaveLength(70)
    drawable.setWaveSpeed(65)
  }
}