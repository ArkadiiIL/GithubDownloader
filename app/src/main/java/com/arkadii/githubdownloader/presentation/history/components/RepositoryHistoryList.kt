package com.arkadii.githubdownloader.presentation.history.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkadii.githubdownloader.domain.model.RepositoryInfo
import com.arkadii.githubdownloader.presentation.Dimens
import kotlinx.coroutines.flow.Flow

@Composable
fun RepositoryHistoryList(
    modifier: Modifier = Modifier,
    repositoryInfo: Flow<List<RepositoryInfo>>
) {
    val repositoryInfoList by repositoryInfo.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
        contentPadding = PaddingValues(all = Dimens.ExtraSmallPadding1)
    ) {
        items(
            count = repositoryInfoList.size,
        ) { index ->
            repositoryInfoList.getOrNull(index)?.let { repository ->
                RepositoryInfoHistoryCard(
                    repositoryInfo = repository,
                )
            }
        }
    }
}

