package com.example.civicpulse.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.civicpulse.data.repository.CivicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: CivicRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    private var currentPage = 1
    private var isRequestInProgress = false

    init {
        loadInitial()
    }

    private fun loadInitial() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            isRequestInProgress = true

            val result = repository.getRepositories(page = currentPage)

            _uiState.value = result.fold(
                onSuccess = {
                    HomeUiState.Success(
                        repos = it,
                        isLoadingMore = false
                    )
                },
                onFailure = {
                    HomeUiState.Error(it.message ?: "Unknown error")
                }
            )

            isRequestInProgress = false
        }
    }

    fun loadNextPage() {
        val currentState = _uiState.value
        if (currentState !is HomeUiState.Success) return
        if (isRequestInProgress) return

        viewModelScope.launch {
            isRequestInProgress = true
            _uiState.value = currentState.copy(isLoadingMore = true)

            currentPage++

            val result = repository.getRepositories(page = currentPage)

            _uiState.value = result.fold(
                onSuccess = { newRepos ->
                    HomeUiState.Success(
                        repos = currentState.repos + newRepos,
                        isLoadingMore = false
                    )
                },
                onFailure = {
                    currentState.copy(isLoadingMore = false)
                }
            )

            isRequestInProgress = false
        }
    }

    fun refresh() {
        viewModelScope.launch {
            currentPage = 1
            isRequestInProgress = false

            _uiState.value = HomeUiState.Loading
            repository.clearCache()

            loadInitial()
        }
    }
}
