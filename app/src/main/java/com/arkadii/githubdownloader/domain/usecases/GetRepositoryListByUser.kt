package com.arkadii.githubdownloader.domain.usecases

import com.arkadii.githubdownloader.domain.repository.RepositoryInfoRepository

class GetRepositoryListByUser(private val repository: RepositoryInfoRepository) {
    operator fun invoke(user: String) =
        repository.getRepositoryInfoListByrUser(user = user)
}