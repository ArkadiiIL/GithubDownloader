package com.arkadii.githubdownloader.presentation.nvgraph

sealed class Route(val route: String) {
    data object SearchScreen : Route(route = "searchScreen")
    data object HistoryScreen : Route(route="historyScreen")
}