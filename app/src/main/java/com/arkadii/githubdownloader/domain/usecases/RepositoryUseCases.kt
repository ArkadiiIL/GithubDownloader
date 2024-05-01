package com.arkadii.githubdownloader.domain.usecases

data class RepositoryUseCases(
    val getRepositoryListByUser: GetRepositoryListByUser,
    val downloadRepositoryByUrl: DownloadRepositoryByUrl,
    val getAllDownloadedRepositories: GetAllDownloadedRepositories,
    val insertRepositoryInfo: InsertRepositoryInfo
)