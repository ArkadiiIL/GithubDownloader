package com.arkadii.githubdownloader.domain.usecases

import com.arkadii.githubdownloader.domain.repository.RepositoryInfoRepository

class GetRepositoryListByUserUseCase(private val repository: RepositoryInfoRepository) {
    operator fun invoke(user: String) =
        repository.getRepositoryInfoListByrUser(user = user)
}