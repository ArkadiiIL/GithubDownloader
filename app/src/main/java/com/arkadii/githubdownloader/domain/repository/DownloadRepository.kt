package com.arkadii.githubdownloader.domain.repository

interface DownloadRepository {
    fun downloadRepositoryByUrl(name: String, owner: String): Long
}