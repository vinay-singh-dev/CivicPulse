package com.example.civicpulse.data.remote


import com.example.civicpulse.data.remote.dto.RepoSearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): RepoSearchResponseDto
}