package com.arkadii.githubdownloader.domain.usecases

import com.arkadii.githubdownloader.domain.repository.RepositoryInfoRepository

class GetDownloadUrl(private val repository: RepositoryInfoRepository) {
    suspend operator fun invoke(owner: String, repo: String) =
        repository.getDownloadUrl(owner = owner, repo = repo)
}