package com.example.civicpulse.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.runtime.getValue
import com.example.civicpulse.ui.screen.home.HomeViewModel
import androidx.compose.runtime.snapshotFlow


@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val state by viewModel.uiState.collectAsState<HomeUiState>()
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { index ->
            val totalItems = listState.layoutInfo.totalItemsCount
            if (index != null && index >= totalItems - 3) {
                viewModel.loadNextPage()
            }
        }
    }

    when (state) {
        is HomeUiState.Loading -> CircularProgressIndicator()

        is HomeUiState.Success -> {
            val successState = state as HomeUiState.Success

            LazyColumn(state = listState) {
                items(successState.repos, key = { it.id }) { repo ->
                    Column {
                        Text(repo.name)
                        repo.description?.let { Text(it) }
                        Text("⭐ ${repo.stars}")
                    }
                }

                if (successState.isLoadingMore) {
                    item { CircularProgressIndicator() }
                }
            }
        }

        is HomeUiState.Error -> {
            Text((state as HomeUiState.Error).message)
        }
    }
}

