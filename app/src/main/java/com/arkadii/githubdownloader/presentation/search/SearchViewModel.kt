package com.arkadii.githubdownloader.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arkadii.githubdownloader.domain.usecases.RepositoryInfoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repositoryInfoUseCases: RepositoryInfoUseCases
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
                viewModelScope.launch {
                    repositoryInfoUseCases.getDownloadUrl.invoke(
                        owner = event.repositoryInfo.owner.login,
                        repo = event.repositoryInfo.name
                    )
                }
            }
        }
    }

    private fun searchRepositoryInfo() {
        val repositoryInfoList =
            repositoryInfoUseCases.getRepositoryListByUserUseCase.invoke(
                user = state.value.searchQuery
            )
                .cachedIn(viewModelScope)
        _state.value = state.value.copy(repositoryInfo = repositoryInfoList)
    }
}