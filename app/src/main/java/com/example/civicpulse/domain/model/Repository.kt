package com.example.civicpulse.domain.model

data class Repository(
    val id: Long,
    val name: String,
    val description: String?,
    val stars: Int
)
