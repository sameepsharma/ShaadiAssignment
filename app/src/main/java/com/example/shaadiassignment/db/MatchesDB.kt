package com.example.shaadiassignment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shaadiassignment.db.dao.MatchesDao
import com.example.shaadiassignment.db.entities.MatchesEntity

@Database(entities = [MatchesEntity::class], version = 1, exportSchema = false)
abstract class MatchesDB : RoomDatabase() {

    abstract fun matchesDao(): MatchesDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MatchesDB? = null

        fun getDatabase(context: Context): MatchesDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MatchesDB::class.java,
                    "course_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}