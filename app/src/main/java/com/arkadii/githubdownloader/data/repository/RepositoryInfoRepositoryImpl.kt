package com.arkadii.githubdownloader.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arkadii.githubdownloader.data.api.RepositoryInfoApi
import com.arkadii.githubdownloader.data.api.RepositoryInfoPagingSource
import com.arkadii.githubdownloader.domain.model.RepositoryInfo
import com.arkadii.githubdownloader.domain.repository.RepositoryInfoRepository
import com.arkadii.githubdownloader.util.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class RepositoryInfoRepositoryImpl(private val repositoryInfoApi: RepositoryInfoApi) :
    RepositoryInfoRepository {
    override fun getRepositoryInfoListByrUser(user: String): Flow<PagingData<RepositoryInfo>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                RepositoryInfoPagingSource(repositoryInfoApi = repositoryInfoApi, user = user)
            }
        ).flow
    }
}