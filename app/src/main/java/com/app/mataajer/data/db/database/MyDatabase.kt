package com.app.mataajer.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.mataajer.data.db.daos.PostDao
import com.app.mataajer.data.db.entities.PostLocal


/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 13/09/2022
 */

@Database(entities = [PostLocal::class], version = 1)
abstract class MyDatabase() : RoomDatabase() {
    abstract fun postDao(): PostDao
}