package com.example.civicpulse.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.civicpulse.data.repository.CivicRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = CivicRepositoryImpl()

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        fetchRepos()
    }

    private fun fetchRepos() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            val result = repository.getRepositories()
            _uiState.value = result.fold(
                onSuccess = { HomeUiState.Success(it) },
                onFailure = { HomeUiState.Error(it.message ?: "Unknown error") }
            )
        }
    }
}