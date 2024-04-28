package com.arkadii.githubdownloader.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.arkadii.githubdownloader.presentation.Dimens.MediumPadding1
import com.arkadii.githubdownloader.presentation.common.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchRepositoryInfo) }
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.repositoryInfo?.let {
            val repositoryInfo = it.collectAsLazyPagingItems()
            RepositoryInfoList(repositoryInfo = repositoryInfo)
        }
    }
}
