package com.example.civicpulse.data.repository


import com.example.civicpulse.data.remote.dto.RepoDto

interface CivicRepository {
    suspend fun getRepositories(): Result<List<RepoDto>>
}