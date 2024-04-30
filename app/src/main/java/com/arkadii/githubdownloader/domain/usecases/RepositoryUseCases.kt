package com.arkadii.githubdownloader.domain.usecases

data class RepositoryUseCases(
    val getRepositoryListByUserUseCase: GetRepositoryListByUserUseCase,
    val getDownloadUrl: GetDownloadUrl,
    val downloadRepositoryByUrl: DownloadRepositoryByUrl
)