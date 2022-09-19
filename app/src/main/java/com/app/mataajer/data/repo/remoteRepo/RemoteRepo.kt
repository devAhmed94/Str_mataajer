package com.app.mataajer.data.repo.remoteRepo

import com.app.mataajer.application.core.BaseRsm
import com.app.mataajer.domain.remote.Post
import retrofit2.Response


/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 18/09/2022
 */
interface RemoteRepo {
    suspend fun getPosts(): Response<BaseRsm<Post>>
}