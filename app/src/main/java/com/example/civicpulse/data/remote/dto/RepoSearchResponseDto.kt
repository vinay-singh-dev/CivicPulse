package com.example.civicpulse.data.remote.dto

import com.squareup.moshi.Json

data class RepoSearchResponseDto(
    @Json(name = "items")
    val items: List<RepoDto>
)