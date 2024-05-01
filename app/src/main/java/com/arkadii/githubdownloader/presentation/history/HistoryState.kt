package com.arkadii.githubdownloader.presentation.history

import com.arkadii.githubdownloader.domain.model.RepositoryInfo
import kotlinx.coroutines.flow.Flow

data class HistoryState(
    val list: Flow<List<RepositoryInfo>>? = null
)
