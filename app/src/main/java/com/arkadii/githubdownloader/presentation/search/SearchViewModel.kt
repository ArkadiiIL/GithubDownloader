package com.arkadii.githubdownloader.presentation.search

import androidx.lifecycle.ViewModel
import com.arkadii.githubdownloader.domain.usecases.RepositoryInfoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repositoryInfoUseCases: RepositoryInfoUseCases
) : ViewModel() {
}