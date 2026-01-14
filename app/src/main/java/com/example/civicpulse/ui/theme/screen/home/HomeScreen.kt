package com.example.civicpulse.ui.theme.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.civicpulse.ui.theme.screen.home.HomeUiState
import com.example.civicpulse.ui.theme.screen.home.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {

    val state by viewModel.uiState.collectAsState()

    when (state) {
        is HomeUiState.Loading -> {
            CircularProgressIndicator()
        }

        is HomeUiState.Success -> {
            val repos = (state as HomeUiState.Success).repos
            LazyColumn {
                items(repos, key = { it.id }) {
                    Text(text = it.name)
                }
            }
        }

        is HomeUiState.Error -> {
            Text(text = (state as HomeUiState.Error).message)
        }
    }
}