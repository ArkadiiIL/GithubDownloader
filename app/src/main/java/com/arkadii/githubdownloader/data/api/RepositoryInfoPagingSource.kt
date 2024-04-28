package com.arkadii.githubdownloader.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arkadii.githubdownloader.data.api.mapper.ApiMapper
import com.arkadii.githubdownloader.domain.model.RepositoryInfo
import okhttp3.Headers
import retrofit2.HttpException

class RepositoryInfoPagingSource(
    private val repositoryInfoApi: RepositoryInfoApi,
    private val user: String
) :
    PagingSource<Int, RepositoryInfo>() {
    override fun getRefreshKey(state: PagingState<Int, RepositoryInfo>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryInfo> {
        val page = params.key ?: 1
        return try {
            val response = repositoryInfoApi.getRepositoryInfoListByUser(user = user, page = page)
            if (response.isSuccessful && response.body() != null) {
                val hasNextPage = hasNextPage(response.headers())
                val repositoryInfoList = response.body()!!.map(ApiMapper::mapDtoToModel).distinctBy { it.id }

                return LoadResult.Page(
                    data = repositoryInfoList,
                    nextKey = if (!hasNextPage) null else page + 1,
                    prevKey = null
                )
            } else throw HttpException(response)

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

    private fun hasNextPage(headers: Headers): Boolean {
        val linkHeader: String? = headers.get("Link")
        return linkHeader != null && linkHeader.contains("rel=\"next\"")

    }

}