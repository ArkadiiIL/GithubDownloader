package com.arkadii.githubdownloader.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arkadii.githubdownloader.domain.model.RepositoryInfo
import com.arkadii.githubdownloader.domain.usecases.RepositoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repositoryUseCases: RepositoryUseCases
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state
    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SearchRepositoryInfo -> {
                searchRepositoryInfo()
            }

            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.DownloadRepository -> {
                download(event.repositoryInfo)
            }

            is SearchEvent.PermissionGranted -> {
                if (event.permissionGranted) {
                    _state.value =
                        state.value.copy(permissionGranted = true, permissionError = false)
                } else {
                    _state.value =
                        state.value.copy(
                            permissionGranted = false,
                            permissionError = true
                        )
                }
            }
        }
    }

    private fun download(repositoryInfo: RepositoryInfo) {
        repositoryUseCases.downloadRepositoryByUrl.invoke(
            name = repositoryInfo.name,
            owner = repositoryInfo.ownerLogin
        )
        viewModelScope.launch {
            repositoryUseCases.insertRepositoryInfo.invoke(repositoryInfo)
        }
    }

    private fun searchRepositoryInfo() {
        val repositoryInfoList =
            repositoryUseCases.getRepositoryListByUser.invoke(
                user = state.value.searchQuery
            )
                .cachedIn(viewModelScope)
        _state.value = state.value.copy(repositoryInfo = repositoryInfoList)
    }
}