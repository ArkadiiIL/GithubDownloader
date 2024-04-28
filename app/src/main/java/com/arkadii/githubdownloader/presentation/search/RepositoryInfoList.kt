package com.arkadii.githubdownloader.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.arkadii.githubdownloader.domain.model.RepositoryInfo
import com.arkadii.githubdownloader.presentation.Dimens.ExtraSmallPadding1
import com.arkadii.githubdownloader.presentation.Dimens.MediumPadding1
import com.arkadii.githubdownloader.presentation.common.RepositoryInfoCard

@Composable
fun RepositoryInfoList(
    modifier: Modifier = Modifier,
    repositoryInfo: LazyPagingItems<RepositoryInfo>
) {
    val handlePagingResult = handlePagingResult(repositoryInfo)

    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding1)
        ) {
            items(
                count = repositoryInfo.itemCount,
            ) {
                repositoryInfo[it]?.let { repository ->
                    RepositoryInfoCard(repositoryInfo = repository, onTitleClick = {})
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(repositoryInfo: LazyPagingItems<RepositoryInfo>): Boolean {
    val loadState = repositoryInfo.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {

            false
        }

        error != null -> {

            false
        }

        else -> {
            true
        }
    }
}
