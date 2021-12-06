package com.example.shaadiassignment.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "matches_table")
data class MatchesEntity(
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "dob") val dob : String,
    @ColumnInfo(name = "largeThumbnail") val largeThumbnail: String,
    @ColumnInfo(name = "accepted") val accepted: Boolean
)