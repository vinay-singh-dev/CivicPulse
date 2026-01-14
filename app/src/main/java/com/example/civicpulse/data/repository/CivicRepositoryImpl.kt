package com.example.civicpulse.data.repository


import com.example.civicpulse.data.remote.RetrofitClient
import com.example.civicpulse.data.remote.dto.RepoDto

class CivicRepositoryImpl : CivicRepository {

    override suspend fun getRepositories(): Result<List<RepoDto>> {
        return try {
            val response = RetrofitClient.apiService.searchRepositories(
                query = "android",
                page = 1,
                perPage = 20
            )
            Result.success(response.items)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}