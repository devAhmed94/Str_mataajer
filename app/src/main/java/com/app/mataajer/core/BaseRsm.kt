package com.app.mataajer.core

import com.google.gson.annotations.SerializedName

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */
data class BaseRsm<T>(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T?,
    @SerializedName("pagination")
    val pagination: Pagination?
) {

  data class Pagination(
      @SerializedName("total")
      val total: Int,
      @SerializedName("currentPage")
      val currentPage: Int,
      @SerializedName("lastPage")
      val lastPage: Int,
      @SerializedName("perPage")
      val perPage: Int
  )
}