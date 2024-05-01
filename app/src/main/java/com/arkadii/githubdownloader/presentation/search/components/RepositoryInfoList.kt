package com.arkadii.githubdownloader.presentation.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.arkadii.githubdownloader.domain.model.RepositoryInfo
import com.arkadii.githubdownloader.presentation.Dimens.ExtraSmallPadding1
import com.arkadii.githubdownloader.presentation.Dimens.MediumPadding1
import com.arkadii.githubdownloader.presentation.common.ErrorScreen
import com.arkadii.githubdownloader.presentation.common.RepositoryInfoCardShimmerEffect
import com.arkadii.githubdownloader.presentation.search.SearchState

@Composable
fun RepositoryInfoList(
    modifier: Modifier = Modifier,
    repositoryInfo: LazyPagingItems<RepositoryInfo>,
    state: SearchState,
    onDownloadButtonClick: (RepositoryInfo) -> Unit,
) {
    val handlePagingResult = handlePagingResult(repositoryInfo)

    if (state.permissionError) {
        ShowPermissionError()
    }

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
                    RepositoryInfoCard(
                        repositoryInfo = repository,
                        onDownloadButtonClick = onDownloadButtonClick
                    )
                }
            }
        }
    }
}

@Composable
fun ShowPermissionError() {
    ErrorScreen(SecurityException())
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
            ShimmerEffect()
            false
        }

        error != null -> {
            ErrorScreen(error = error.error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.SpaceAround) {
        repeat(10) {
            RepositoryInfoCardShimmerEffect(
                modifier = Modifier.padding(MediumPadding1)
            )
        }

    }
}
