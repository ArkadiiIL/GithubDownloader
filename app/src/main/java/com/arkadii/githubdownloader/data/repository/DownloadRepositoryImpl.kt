package com.arkadii.githubdownloader.data.repository

import com.arkadii.githubdownloader.data.downloader.MyDownloadManager
import com.arkadii.githubdownloader.domain.repository.DownloadRepository
import com.arkadii.githubdownloader.util.Constants.GITHUB_BASE_API_URL
import com.arkadii.githubdownloader.util.Constants.GITHUB_DOWNLOAD_ZIP_DIVIDE
import com.arkadii.githubdownloader.util.Constants.GITHUB_DOWNLOAD_ZIP_END
import com.arkadii.githubdownloader.util.Constants.GITHUB_DOWNLOAD_ZIP_START

class DownloadRepositoryImpl(private val myDownloadManager: MyDownloadManager): DownloadRepository {
    override fun downloadRepositoryByUrl(name: String, owner: String): Long {
        val url = getDownloadUrl(name, owner)
        return myDownloadManager.downloadRepository(url, name)
    }

    private fun getDownloadUrl(name: String, owner: String): String {
        return GITHUB_BASE_API_URL +
                GITHUB_DOWNLOAD_ZIP_START +
                owner +
                GITHUB_DOWNLOAD_ZIP_DIVIDE +
                name +
                GITHUB_DOWNLOAD_ZIP_END

    }
}