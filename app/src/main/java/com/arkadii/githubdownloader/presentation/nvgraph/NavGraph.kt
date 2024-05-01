package com.arkadii.githubdownloader.presentation.nvgraph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arkadii.githubdownloader.presentation.history.components.HistoryScreen
import com.arkadii.githubdownloader.presentation.history.HistoryVIewModel
import com.arkadii.githubdownloader.presentation.search.SearchScreen
import com.arkadii.githubdownloader.presentation.search.SearchViewModel

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigation(navController = navController) }) {
        Box(modifier = Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = startDestination) {
                composable(Route.SearchScreen.route) {
                    val viewModel: SearchViewModel = hiltViewModel()
                    SearchScreen(state = viewModel.state.value, event = viewModel::onEvent)
                }
                composable(Route.HistoryScreen.route) {
                    val viewModel: HistoryVIewModel = hiltViewModel()
                    HistoryScreen(state = viewModel.state)

                }
            }
        }

    }


}