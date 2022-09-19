package com.app.mataajer.data.network

import com.app.mataajer.application.core.BaseRsm
import com.app.mataajer.domain.remote.Post
import retrofit2.Response
import retrofit2.http.GET

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */
interface ApiService {

  @GET("api/mobile/Labs/GetOnlyPatientLabOrdersByPagination")
  suspend fun getPosts(): Response<BaseRsm<Post>>
}