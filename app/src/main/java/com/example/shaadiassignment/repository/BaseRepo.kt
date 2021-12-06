package com.example.shaadiassignment.repository

import com.example.shaadiassignment.db.entities.MatchesEntity

internal interface BaseRepo {
    fun getMatches(
        numMatches: Int
    ): List<MatchesEntity>
}