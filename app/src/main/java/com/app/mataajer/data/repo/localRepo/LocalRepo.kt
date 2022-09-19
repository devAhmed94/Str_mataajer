package com.app.mataajer.data.repo.localRepo

import com.app.mataajer.data.db.entities.PostLocal


/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 13/09/2022
 */
interface LocalRepo {
    suspend fun getPostsFromLocal(): List<PostLocal>
    suspend fun insertToLocal(post: PostLocal)
}