package com.arkadii.githubdownloader.domain.repository

interface DownloadRepository {
    fun downloadFromUrl(url: String, fileName: String)
}