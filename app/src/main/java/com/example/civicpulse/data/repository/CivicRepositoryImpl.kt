package com.example.civicpulse.data.repository

import android.util.Log
import com.example.civicpulse.data.mapper.toDomain
import com.example.civicpulse.data.remote.RetrofitClient
import com.example.civicpulse.domain.model.Repository

class CivicRepositoryImpl : CivicRepository {

    override suspend fun getRepositories(page: Int): Result<List<Repository>> {
        return try {
            Log.d("Repo", "Fetching page = $page")
            val response = RetrofitClient.apiService.searchRepositories(
                query = "android",
                page = page,
                perPage = 20
            )

            // DTO → Domain mapping happens HERE
            val domainRepos = response.items.map { it.toDomain() }

            Result.success(domainRepos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

