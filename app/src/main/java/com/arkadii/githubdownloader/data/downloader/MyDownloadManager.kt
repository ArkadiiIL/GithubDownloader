package com.arkadii.githubdownloader.data.downloader

import android.content.Context

interface MyDownloadManager {
    fun downloadRepository(url: String, fileName: String): Long
}