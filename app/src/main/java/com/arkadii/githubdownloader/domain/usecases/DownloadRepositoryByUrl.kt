package com.arkadii.githubdownloader.domain.usecases

import com.arkadii.githubdownloader.domain.repository.DownloadRepository

class DownloadRepositoryByUrl(private val repository: DownloadRepository) {
    operator fun invoke(name: String, owner: String) =
        repository.downloadRepositoryByUrl(name, owner)
}