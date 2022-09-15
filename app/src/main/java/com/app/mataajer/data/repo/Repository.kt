package com.app.mataajer.data.repo

import com.app.mataajer.data.db.DBHelperImpl
import com.app.mataajer.data.db.database.MyDatabase
import com.app.mataajer.data.db.entities.Post
import com.app.mataajer.data.network.RestClient


/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 13/09/2022
 */
class Repository(private val dbHelperImpl: DBHelperImpl) {

    suspend fun getPosts() = RestClient.api.getPosts()
    suspend fun insertPost(post: Post) = dbHelperImpl.insertAll(post)
}