package com.app.mataajer.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.mataajer.data.db.daos.PostDao
import com.app.mataajer.data.db.entities.Post


/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 13/09/2022
 */

@Database(entities = [Post::class], version = 1)
abstract class MyDatabase() : RoomDatabase() {

    abstract fun postDao(): PostDao

    object DatabaseBuilder {

        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            if (INSTANCE == null) {
                synchronized(MyDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MyDatabase::class.java,
                "my_database"
            ).build()

    }
}