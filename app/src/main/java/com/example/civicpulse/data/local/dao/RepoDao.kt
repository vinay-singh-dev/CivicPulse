package com.example.civicpulse.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.civicpulse.data.local.entity.RepoEntity

@Dao
interface RepoDao {

    @Query("SELECT * FROM repos")
    suspend fun getAllRepos(): List<RepoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repos: List<RepoEntity>)

    @Query("DELETE FROM repos")
    suspend fun clearRepos()
}
