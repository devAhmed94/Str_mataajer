package com.app.mataajer.data.db

import com.app.mataajer.data.db.database.MyDatabase
import com.app.mataajer.data.db.entities.Post


/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 13/09/2022
 */
class DBHelperImpl(private val appDatabase: MyDatabase) : DBHelper {
    override suspend fun getPosts(): List<Post> = appDatabase.postDao().getAll()
    override suspend fun insertAll(post: Post) = appDatabase.postDao().insertAll(post)

}