package com.example.shaadiassignment.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shaadiassignment.db.entities.Match
import com.example.shaadiassignment.db.entities.MatchesEntity

@Dao
interface MatchesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatches(list: List<MatchesEntity>)

    @Query("select * from matches_table")
    fun getSavedMatches(): List<MatchesEntity>

    @Query("update matches_table set accepted = 0 where id like :id")
    fun updateAccepted(id: Int)

}