package com.arkadii.githubdownloader.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arkadii.githubdownloader.data.api.RepositoryInfoApi
import com.arkadii.githubdownloader.data.api.RepositoryInfoPagingSource
import com.arkadii.githubdownloader.data.local.RepositoryInfoDao
import com.arkadii.githubdownloader.data.mapper.DataMapper
import com.arkadii.githubdownloader.domain.model.RepositoryInfo
import com.arkadii.githubdownloader.domain.repository.RepositoryInfoRepository
import com.arkadii.githubdownloader.util.Constants.LOCATION_HEADER
import com.arkadii.githubdownloader.util.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class RepositoryInfoRepositoryImpl(
    private val repositoryInfoApi: RepositoryInfoApi,
    private val repositoryInfoDao: RepositoryInfoDao
    ) : RepositoryInfoRepository {
    override fun getRepositoryInfoListByrUser(user: String): Flow<PagingData<RepositoryInfo>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                RepositoryInfoPagingSource(repositoryInfoApi = repositoryInfoApi, user = user)
            }
        ).flow
    }

    override suspend fun insertRepositoryInfo(repositoryInfo: RepositoryInfo) {
        repositoryInfoDao.insertRepository(DataMapper.mapModelToEntity(repositoryInfo))
    }

    override fun getAllDownloadedRepositories(): Flow<List<RepositoryInfo>> =
        repositoryInfoDao.getAllRepositories().map { entities ->
            entities.map { DataMapper.mapEntityToModel(it) }
        }
}