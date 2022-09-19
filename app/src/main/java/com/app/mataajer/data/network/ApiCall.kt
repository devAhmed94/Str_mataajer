package com.app.mataajer.data.network

import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.app.mataajer.R
import com.app.mataajer.application.app.getStringByLocal
import com.app.mataajer.application.core.BaseRsm
import com.app.mataajer.data.network.Resource.Companion.failure
import com.app.mataajer.data.network.Resource.Companion.loading
import com.app.mataajer.data.network.Resource.Companion.success
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.io.IOException

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */

fun <T> loadData(request: suspend () -> Response<BaseRsm<T>>) = liveData(Dispatchers.IO) {
  emit(loading())
  try {
    val response = request.invoke()
    val rsm = if (response.isSuccessful) response.body() else {
      val type = object : TypeToken<BaseRsm<T>>() {}.type
      Gson().fromJson(response.errorBody()!!.charStream(), type) as BaseRsm<T>
    }

    if (response.isSuccessful && rsm != null) when (rsm.status) {
      0 -> emit(failure(rsm.message))
      1 -> emit(success(rsm.data, rsm.message, rsm.pagination))
    } else emit(failure(rsm?.message ?: getStringByLocal(
        R.string.network_service_error, response.code(), response.message())))
  } catch (e: Exception) {
    emit(failure(getErrorMsg(e)))
  }
}

/*fun loadGeoData(request: suspend () -> Response<GeoCodingRsm>) = liveData(Dispatchers.IO) {
  emit(Resource.loading())
  try {
    val response = request.invoke()
    val rsm = response.body()

    if (response.isSuccessful && rsm != null) {
      when (rsm.status) {
        "OK" -> emit(success(rsm, ""))
        else -> emit(failure(rsm.errorMessage))
      }
    } else emit(failure(rsm?.errorMessage
        ?: getStringByLocal(R.string.network_service_error,
            response.code(), response.message())))
  } catch (e: Exception) {
    emit(failure(getErrorMsg(e)))
  }
}*/

fun getErrorMsg(e: Exception) = when (e) {
  is IOException -> // Timeout
    getStringByLocal(R.string.network_check_connection)
  is RuntimeException -> // Unexpected Json response from server
    getStringByLocal(R.string.network_unexpected_response)
  else -> // Other error
    getStringByLocal(R.string.network_server_unreachable)
}