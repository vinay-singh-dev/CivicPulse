package com.example.civicpulse.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoSearchResponseDto(
    @Json(name = "items")
    val items: List<RepoDto>
)
