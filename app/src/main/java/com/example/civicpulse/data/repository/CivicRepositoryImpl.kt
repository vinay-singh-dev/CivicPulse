package com.example.civicpulse.data.repository

import com.example.civicpulse.data.local.dao.RepoDao
import com.example.civicpulse.data.mapper.toDomain
import com.example.civicpulse.data.mapper.toEntity
import com.example.civicpulse.data.remote.ApiService
import com.example.civicpulse.data.remote.RetrofitClient
import com.example.civicpulse.domain.model.Repository

class CivicRepositoryImpl(
    private val api: ApiService,
    private val dao: RepoDao
) : CivicRepository {

    override suspend fun getRepositories(page: Int): Result<List<Repository>> {
        return try {
            val response = api.searchRepositories(
                query = "android",
                page = page,
                perPage = 20
            )

            val domain = response.items.map { it.toDomain() }
            val entities = domain.map { it.toEntity() }

            dao.insertRepos(entities)

            val cached = dao.getAllRepos().map { it.toDomain() }
            Result.success(cached)

        } catch (e: Exception) {
            val cached = dao.getAllRepos().map { it.toDomain() }
            if (cached.isNotEmpty()) {
                Result.success(cached)
            } else {
                Result.failure(e)
            }
        }
    }
}


