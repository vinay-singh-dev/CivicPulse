package com.example.civicpulse.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    // 🔥 SIDE EFFECT — OUTSIDE LazyColumn
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
        is HomeUiState.Loading -> {
            CircularProgressIndicator()
        }

        is HomeUiState.Success -> {
            val successState = state as HomeUiState.Success

            LazyColumn(state = listState) {
                items(
                    items = successState.repos,
                    key = { it.id }
                ) { repo ->
                    Column {
                        Text(text = repo.name)
                        repo.description?.let {
                            Text(text = it)
                        }
                        Text(text = "⭐ ${repo.stars}")
                    }
                }

                // ✅ FOOTER SPINNER
                if (successState.isLoadingMore) {
                    item {
                        CircularProgressIndicator()
                    }
                }
            }
        }

        is HomeUiState.Error -> {
            Text(text = (state as HomeUiState.Error).message)
        }
    }
}
