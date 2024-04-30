package com.arkadii.githubdownloader.data.downloader;

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.arkadii.githubdownloader.R
import com.arkadii.githubdownloader.util.Constants.ZIP_MIME_TYPE


class MyDownloadManagerImpl(private val context: Context) : MyDownloadManager {
    override fun downloadRepository(url: String, fileName: String): Long {
        val request = DownloadManager.Request(Uri.parse(url)).apply {
            setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI
            )
            setTitle(fileName)
            setDescription(context.getString(R.string.download_description))
            setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setMimeType(ZIP_MIME_TYPE)
        }

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        return downloadManager.enqueue(request)
    }
}
