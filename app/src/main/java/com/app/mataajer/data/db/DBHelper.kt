package com.app.mataajer.data.db

import com.app.mataajer.data.db.entities.Post


/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 13/09/2022
 */
interface DBHelper {
    suspend fun getPosts(): List<Post>

    suspend fun insertAll(post: Post)
}