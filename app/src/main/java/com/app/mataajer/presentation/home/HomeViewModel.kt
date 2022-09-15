package com.app.mataajer.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mataajer.data.db.entities.Post
import com.app.mataajer.data.network.loadData
import com.app.mataajer.data.repo.Repository
import kotlinx.coroutines.launch


/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 13/09/2022
 */
class HomeViewModel(private val repo: Repository) : ViewModel() {

    fun getPosts() = loadData { repo.getPosts() }

    fun savePost(post: Post)=viewModelScope.launch{
        repo.insertPost(post)
    }
}