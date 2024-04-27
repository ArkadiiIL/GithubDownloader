package com.arkadii.githubdownloader.presentation.nvgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Route.SearchScreen.route) {}
        composable(Route.HistoryScreen.route) {}
    }

}