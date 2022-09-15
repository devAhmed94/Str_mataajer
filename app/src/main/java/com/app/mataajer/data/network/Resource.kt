package com.app.mataajer.data.network

import com.app.mataajer.core.BaseRsm

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */
data class Resource<out T>(
    @Status val status: Int,
    val message: String = "",
    val data: T? = null,
    val pagination: BaseRsm.Pagination? = null
) {

  companion object {

    fun loading(): Resource<Nothing> = Resource(LOADING)

    fun <T> success(data: T?, message: String, pagination: BaseRsm.Pagination? = null): Resource<T> =
        Resource(status = SUCCESS, data = data, message = message, pagination = pagination)

    fun failure(message: String): Resource<Nothing> = Resource(status = ERROR, message = message)
  }
}