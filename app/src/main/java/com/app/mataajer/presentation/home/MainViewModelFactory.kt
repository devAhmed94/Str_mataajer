package com.app.mataajer.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.mataajer.data.repo.Repository


/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 13/09/2022
 */
class MainViewModelFactory(private val repo:Repository) :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repo) as T
    }
}