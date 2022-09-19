package com.app.mataajer.data.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.app.mataajer.data.db.entities.PostLocal


/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 11/09/2022
 */

@Dao
interface PostDao {

    @Insert
    fun insertAll( post: PostLocal)

    @Query("SELECT * FROM post_table ")
    fun getAll(): List<PostLocal>

    @Delete
    fun delete(post: PostLocal)
}