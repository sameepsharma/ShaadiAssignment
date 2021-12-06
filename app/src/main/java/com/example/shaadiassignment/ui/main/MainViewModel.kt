package com.example.shaadiassignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shaadiassignment.db.entities.Match
import com.example.shaadiassignment.db.entities.MatchesEntity
import com.example.shaadiassignment.repository.MatchesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: MatchesRepo) : ViewModel() {


    fun isAccepeted(entity: MatchesEntity) {

        repo.updateAccepted(entity)

    }

    val isLoading = MutableLiveData<Boolean>(false)

    var matchesLiveData = MutableLiveData<List<MatchesEntity>>(mutableListOf())

    init {
        isLoading.postValue(true)
        repo.scope = viewModelScope
        matchesLiveData.postValue(repo.getMatches(30))
    }

}