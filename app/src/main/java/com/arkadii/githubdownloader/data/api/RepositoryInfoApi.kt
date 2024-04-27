package com.arkadii.githubdownloader.data.api

import com.arkadii.githubdownloader.domain.model.RepositoryInfo
import com.arkadii.githubdownloader.util.Constants.GITHUB_API_VERSION
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoryInfoApi {
    @GET("users/{user}/repos")
    suspend fun getRepositoryInfoListByUser(
        @Header("X-GitHub-Api-Version") apiVersion: String = GITHUB_API_VERSION,
        @Path("user") user: String,
        @Query("page") page: Int
    ): Response<List<RepositoryInfo>>
}