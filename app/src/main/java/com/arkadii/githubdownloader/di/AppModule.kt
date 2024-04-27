package com.arkadii.githubdownloader.di

import com.arkadii.githubdownloader.data.api.RepositoryInfoApi
import com.arkadii.githubdownloader.data.repository.RepositoryInfoRepositoryImpl
import com.arkadii.githubdownloader.domain.repository.RepositoryInfoRepository
import com.arkadii.githubdownloader.domain.usecases.GetRepositoryListByUserUseCase
import com.arkadii.githubdownloader.domain.usecases.RepositoryInfoUseCases
import com.arkadii.githubdownloader.util.Constants.GITHUB_BASE_API_URL
import dagger.Component
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Component
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRepositoryInfoApiInstance(): RepositoryInfoApi {
        return Retrofit.Builder()
            .baseUrl(GITHUB_BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RepositoryInfoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositoryInfoRepository(
        repositoryInfoApi: RepositoryInfoApi
    ): RepositoryInfoRepository {
        return RepositoryInfoRepositoryImpl(repositoryInfoApi)
    }

    @Provides
    @Singleton
    fun provideRepositoryInfoUseCases(
        repositoryInfoRepository: RepositoryInfoRepository
    ): RepositoryInfoUseCases {
        return RepositoryInfoUseCases(
            getRepositoryListByUserUseCase = GetRepositoryListByUserUseCase(
                repositoryInfoRepository
            )
        )
    }
}