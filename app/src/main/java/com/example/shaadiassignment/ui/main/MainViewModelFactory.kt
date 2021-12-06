package com.example.shaadiassignment.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.shaadiassignment.repository.MatchesRepo

class MainViewModelFactory(val repo: MatchesRepo) : NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repo = repo) as T
    }
}