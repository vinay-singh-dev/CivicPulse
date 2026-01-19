package com.example.civicpulse.data.repository


import com.example.civicpulse.data.remote.dto.RepoDto
import com.example.civicpulse.domain.model.Repository

interface CivicRepository {
    suspend fun getRepositories(page : Int ): Result<List<Repository>>
}