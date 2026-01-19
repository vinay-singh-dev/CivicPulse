package com.example.civicpulse.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.civicpulse.data.local.dao.RepoDao
import com.example.civicpulse.data.local.entity.RepoEntity

@Database(
    entities = [RepoEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}
