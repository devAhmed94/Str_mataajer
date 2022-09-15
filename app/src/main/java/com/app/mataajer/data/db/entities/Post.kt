package com.app.mataajer.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 11/09/2022
 */
@Entity(tableName = "post_table")
data class Post(var title: String, var body: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}