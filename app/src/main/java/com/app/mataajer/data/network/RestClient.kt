@file:Suppress("DEPRECATION")

package com.app.mataajer.data.network

import com.app.mataajer.BuildConfig.DEBUG
import com.app.mataajer.data.preferences.fcmToken
import com.app.mataajer.data.utils.LocaleHelper.locale
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */
object RestClient {

  private const val BASE_URL = "http://linekw.net/api/"

  val api: ApiService by lazy {
    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .client(httpClient)
      .build().create(ApiService::class.java)
  }

  private val httpClient by lazy {
    OkHttpClient.Builder()
      .addInterceptor(headerInterceptor())
      .addInterceptor(loggingInterceptor())
//        .authenticator(TokenAuthenticator())
      .build()
  }

  private fun headerInterceptor(): Interceptor {
    return Interceptor {
      val request = it.request().newBuilder()
        .header("Content-Type", "application/json")
        .header("Platform", "android")
        .header("FbToken", fcmToken)
        .header("Lang", locale)
        .header("Authorization", /*user?.authorization ?:*/ "")
        .build()

      it.proceed(request)
    }
  }

  private fun loggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      level = if (DEBUG) BODY else NONE
    }
  }
}

private class TokenAuthenticator : Authenticator {

  override fun authenticate(route: Route?, response: Response): Request {
    val updatedToken = getUpdatedToken()
    return response.request.newBuilder()
      .header("Content-Type", "application/json")
      .header("Platform", "android")
      .header("FbToken", fcmToken)
      .header("Lang", locale)
      .header("Authorization", updatedToken)
      .build()
  }

  private fun getUpdatedToken(): String {
    /*val authTokenResponse = liveData(Dispatchers.IO) { emit(RestClient.api.getAds().body()) }
    val newToken = authTokenResponse.value!!.message*/
    // TODO: 2/19/21 Save new token in shared pref.
    return /*newToken*/ ""
  }
}