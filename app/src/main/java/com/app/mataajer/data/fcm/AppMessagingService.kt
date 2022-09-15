package com.app.mataajer.data.fcm

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.graphics.Bitmap
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.app.mataajer.data.preferences.fcmToken
import com.app.mataajer.data.preferences.notificationsAllowed
import com.app.mataajer.data.utils.extensions.getBitmap
import com.app.mataajer.presentation.splash.SplashActivity
import org.jetbrains.anko.intentFor

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */

private const val TAG = "FCM"
private const val OPEN_REQUEST_CODE = 100

class AppMessagingService : FirebaseMessagingService() {

  override fun onNewToken(token: String) {
    FirebaseMessaging.getInstance().subscribeToTopic("android")
    Log.d(TAG, "Firebase token: $token")
    fcmToken = token
  }

  override fun onMessageReceived(remoteMessage: RemoteMessage) {
    remoteMessage.data.isNotEmpty().let {
      Log.d(TAG, "FCM Message: ${remoteMessage.data}")
      sendNotification(remoteMessage.data)
    }
  }

  private fun sendNotification(data: Map<String, String>) {
    val notification = NotificationUtils(applicationContext)
    val stackBuilder = TaskStackBuilder.create(this)
    val title = data.getValue("title")
    val message = data.getValue("message")
    val image = data.getValue("image")

    when (data["action"]) {
      "open" -> {
        if (notificationsAllowed.not()) return
        stackBuilder.addNextIntent(intentFor<SplashActivity>())
        val pendingIntent = stackBuilder.getPendingIntent(OPEN_REQUEST_CODE, FLAG_UPDATE_CURRENT)
        downloadImage(pendingIntent, notification, title, message, image)
      }
    }
  }

  private fun downloadImage(
    pendingIntent: PendingIntent?,
    notification: NotificationUtils,
    title: String,
    message: String,
    image: String
  ) {
    if (image.isNotEmpty())
      getBitmap(image, { notify(pendingIntent, notification, title, message, it) }) {
        notify(pendingIntent, notification, title, message, null)
      }
    else notify(pendingIntent, notification, title, message, null)
  }

  private fun notify(
    pendingIntent: PendingIntent?,
    notification: NotificationUtils,
    title: String,
    message: String,
    image: Bitmap?
  ) = with(NotificationManagerCompat.from(this)) {
    pendingIntent?.let {
      val id = System.currentTimeMillis().toInt()
      notify(id, notification.show(title, message, image, it))
    }
  }
}