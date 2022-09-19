package com.app.mataajer.data.repo.localRepo

import com.app.mataajer.data.db.database.MyDatabase
import com.app.mataajer.data.db.entities.PostLocal
import com.app.mataajer.data.repo.localRepo.LocalRepo


/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 13/09/2022
 */
class LocalRepoImp(private val appDatabase: MyDatabase) : LocalRepo {
    override suspend fun getPostsFromLocal(): List<PostLocal> = appDatabase.postDao().getAll()
    override suspend fun insertToLocal(post: PostLocal) = appDatabase.postDao().insertAll(post)

}