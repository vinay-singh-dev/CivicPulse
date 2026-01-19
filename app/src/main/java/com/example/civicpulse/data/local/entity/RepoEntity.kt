package com.example.civicpulse.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class RepoEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String?,
    val stars: Int
)
