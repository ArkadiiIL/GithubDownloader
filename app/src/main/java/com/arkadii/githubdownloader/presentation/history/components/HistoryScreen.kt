package com.arkadii.githubdownloader.presentation.history.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.arkadii.githubdownloader.presentation.history.HistoryState

@Composable
fun HistoryScreen(state: MutableState<HistoryState>) {
    Box(Modifier.fillMaxSize()) {
        state.value.list?.let {
            RepositoryHistoryList(repositoryInfo = it)
        }
    }

}
