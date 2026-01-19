package com.example.civicpulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.civicpulse.data.local.database.AppDatabase
import com.example.civicpulse.data.remote.RetrofitClient
import com.example.civicpulse.data.repository.CivicRepositoryImpl
import com.example.civicpulse.ui.screen.home.HomeScreen
import com.example.civicpulse.ui.screen.home.HomeViewModel
import com.example.civicpulse.ui.screen.home.HomeViewModelFactory
import com.example.civicpulse.ui.theme.CivicPulseTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CivicPulseTheme {

                // 1. Create database (once)
                val db = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    "civicpulse.db"
                ).build()

                // 2. Create repository
                val repository = CivicRepositoryImpl(
                    api = RetrofitClient.apiService,
                    dao = db.repoDao()
                )

                // 3. Create ViewModel with factory
                val homeViewModel = viewModel<HomeViewModel>(
                    factory = HomeViewModelFactory(repository)
                )



                Surface(color = MaterialTheme.colorScheme.background) {
                    HomeScreen(viewModel = homeViewModel)
                }
            }
        }
    }
}
