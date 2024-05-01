package com.arkadii.githubdownloader.domain.usecases

import com.arkadii.githubdownloader.domain.repository.RepositoryInfoRepository

class GetAllDownloadedRepositories(private val repository: RepositoryInfoRepository) {
    operator fun invoke() = repository.getAllDownloadedRepositories()
}