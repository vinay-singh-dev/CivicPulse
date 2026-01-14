package com.example.civicpulse.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {

    val state by viewModel.uiState.collectAsState()

    when (state) {

        is HomeUiState.Loading -> {
            CircularProgressIndicator()
        }

        is HomeUiState.Success -> {
            val repos = (state as HomeUiState.Success).repos

            LazyColumn {
                items(repos, key = { it.id }) { repo ->
                    Column {
                        Text(text = repo.name)

                        repo.description?.let {
                            Text(text = it)
                        }

                        Text(text = "⭐ ${repo.stars}")
                    }
                }
            }
        }

        is HomeUiState.Error -> {
            Text(text = (state as HomeUiState.Error).message)
        }
    }
}