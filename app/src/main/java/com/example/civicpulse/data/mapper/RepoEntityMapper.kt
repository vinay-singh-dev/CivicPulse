package com.example.civicpulse.data.mapper

import com.example.civicpulse.data.local.entity.RepoEntity
import com.example.civicpulse.domain.model.Repository


    fun RepoEntity.toDomain(): Repository =
        Repository(id, name, description, stars)

    fun Repository.toEntity(): RepoEntity =
        RepoEntity(id, name, description, stars)

