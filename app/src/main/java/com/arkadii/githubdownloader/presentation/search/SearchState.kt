package com.arkadii.githubdownloader.presentation.search

import androidx.paging.PagingData
import com.arkadii.githubdownloader.domain.model.RepositoryInfo
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val repositoryInfo: Flow<PagingData<RepositoryInfo>>? = null
)
