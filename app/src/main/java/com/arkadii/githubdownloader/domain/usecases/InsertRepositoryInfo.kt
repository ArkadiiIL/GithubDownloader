package com.arkadii.githubdownloader.domain.usecases

import com.arkadii.githubdownloader.domain.model.RepositoryInfo
import com.arkadii.githubdownloader.domain.repository.RepositoryInfoRepository

class InsertRepositoryInfo(private val repository: RepositoryInfoRepository) {
    suspend operator fun invoke(repositoryInfo: RepositoryInfo) =
        repository.insertRepositoryInfo(repositoryInfo)
}