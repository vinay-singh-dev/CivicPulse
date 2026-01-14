package com.example.civicpulse.data.mapper

import com.example.civicpulse.data.remote.dto.RepoDto
import com.example.civicpulse.domain.model.Repository

fun RepoDto.toDomain(): Repository {
    return Repository(
        id = id,
        name = name,
        description = description,
        stars = stars
    )
}
