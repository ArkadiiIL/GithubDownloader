package com.arkadii.githubdownloader.domain.repository

import androidx.paging.PagingData
import com.arkadii.githubdownloader.domain.model.RepositoryInfo
import kotlinx.coroutines.flow.Flow

interface RepositoryInfoRepository {
    fun getRepositoryInfoListByrUser(user: String) : Flow<PagingData<RepositoryInfo>>
    suspend fun getDownloadUrl(owner: String, repo: String)
}