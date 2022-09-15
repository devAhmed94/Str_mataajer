package com.app.mataajer.data.utils.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.app.mataajer.data.utils.GlideApp

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */

/**
 * Load image from drawable
 * @param icon
 */
fun ImageView.load(@DrawableRes icon: Int) {
  GlideApp.with(this).load(icon).into(this)
}

/**
 * Load image into ImageView with Radius
 * @param imageUrl
 * @param radius default is 1
 */
fun ImageView.load(imageUrl: String, radius: Int = 1) {
  GlideApp.with(this).load(imageUrl)
      .apply(RequestOptions.bitmapTransform(RoundedCorners(radius))).into(this)
}

/**
 * Load image into ImageView with listener
 * @param imageUrl
 * @param onSucceed
 * @param onFailed
 */
fun ImageView.load(imageUrl: String, onSucceed: () -> Unit, onFailed: () -> Unit) {
  GlideApp.with(this).load(imageUrl).listener(object : RequestListener<Drawable> {
    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                 dataSource: DataSource?, isFirstResource: Boolean): Boolean {
      onSucceed()
      return false
    }

    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?,
                              isFirstResource: Boolean): Boolean {
      onFailed()
      return false
    }
  }).into(this)
}

/**
 * Load image into ImageView with radius and listener
 * @param imageUrl
 * @param radius default is 1
 * @param onSucceed
 * @param onFailed
 */
fun ImageView.load(imageUrl: String, radius: Int = 1, onSucceed: () -> Unit, onFailed: () -> Unit) {
  GlideApp.with(this).load(imageUrl).listener(object : RequestListener<Drawable> {
    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                 dataSource: DataSource?, isFirstResource: Boolean): Boolean {
      onSucceed()
      return false
    }

    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?,
                              isFirstResource: Boolean): Boolean {
      onFailed()
      return false
    }
  }).apply(RequestOptions.bitmapTransform(RoundedCorners(radius))).into(this)
}

/**
 * Load image as Drawable
 * @param imageUrl
 * @param onSucceed
 * @param onFailed
 */
fun View.getDrawable(imageUrl: String,
                     onSucceed: (resource: Drawable) -> Unit,
                     onFailed: () -> Unit) {
  GlideApp.with(this).load(imageUrl).into(object : CustomTarget<Drawable>() {
    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
      onSucceed(resource)
    }

    override fun onLoadCleared(placeholder: Drawable?) {
      onFailed()
    }
  })
}

fun Context.getBitmap(
  imageUrl: String,
  onSucceed: (resource: Bitmap) -> Unit,
  onFailed: () -> Unit
) {
  GlideApp.with(this).asBitmap().load(imageUrl).into(object : CustomTarget<Bitmap>() {
    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
      onSucceed(resource)
    }

    override fun onLoadCleared(placeholder: Drawable?) {}

    override fun onLoadFailed(errorDrawable: Drawable?) {
      super.onLoadFailed(errorDrawable)
      onFailed()
    }
  })
}