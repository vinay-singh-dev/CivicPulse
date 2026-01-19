package com.example.civicpulse.ui.screen.home

import com.example.civicpulse.domain.model.Repository

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(
        val repos: List<Repository>,
        val isLoadingMore : Boolean
    ) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}