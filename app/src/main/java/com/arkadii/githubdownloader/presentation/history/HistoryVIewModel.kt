package com.arkadii.githubdownloader.presentation.history

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.arkadii.githubdownloader.domain.model.RepositoryInfo
import com.arkadii.githubdownloader.domain.usecases.RepositoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HistoryVIewModel @Inject constructor(
    private val repositoryInfoUseCases: RepositoryUseCases
) : ViewModel() {
    private val _state = mutableStateOf(HistoryState())
    val state: MutableState<HistoryState> = _state

    init {
        val list = repositoryInfoUseCases.getAllDownloadedRepositories.invoke()
        _state.value = state.value.copy(list = list)
    }
}