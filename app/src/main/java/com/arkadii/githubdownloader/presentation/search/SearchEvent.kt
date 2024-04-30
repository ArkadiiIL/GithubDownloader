package com.arkadii.githubdownloader.presentation.search

import com.arkadii.githubdownloader.domain.model.RepositoryInfo

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String): SearchEvent()

    data object SearchRepositoryInfo: SearchEvent()

    data class DownloadRepository(val repositoryInfo: RepositoryInfo): SearchEvent()

    data class PermissionGranted(val permissionGranted: Boolean): SearchEvent()
}