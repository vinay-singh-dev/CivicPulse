package com.example.civicpulse.ui.screen.home

import com.example.civicpulse.data.remote.dto.RepoDto

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val repos: List<RepoDto>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}