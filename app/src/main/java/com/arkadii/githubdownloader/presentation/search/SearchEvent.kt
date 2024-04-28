package com.arkadii.githubdownloader.presentation.search

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String): SearchEvent()

    data object SearchRepositoryInfo: SearchEvent()
}