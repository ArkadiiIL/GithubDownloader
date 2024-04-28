package com.arkadii.githubdownloader.presentation.nvgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arkadii.githubdownloader.presentation.search.SearchScreen
import com.arkadii.githubdownloader.presentation.search.SearchViewModel

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Route.SearchScreen.route) {
            val viewModel: SearchViewModel = hiltViewModel()
            SearchScreen(state = viewModel.state.value, event = viewModel::onEvent)
        }
        composable(Route.HistoryScreen.route) {}
    }

}