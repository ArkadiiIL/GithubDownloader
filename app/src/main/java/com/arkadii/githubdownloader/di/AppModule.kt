package com.arkadii.githubdownloader.di

import android.app.DownloadManager
import android.content.Context
import com.arkadii.githubdownloader.data.api.RepositoryInfoApi
import com.arkadii.githubdownloader.data.downloader.MyDownloadManager
import com.arkadii.githubdownloader.data.downloader.MyDownloadManagerImpl
import com.arkadii.githubdownloader.data.repository.DownloadRepositoryImpl
import com.arkadii.githubdownloader.data.repository.RepositoryInfoRepositoryImpl
import com.arkadii.githubdownloader.domain.repository.DownloadRepository
import com.arkadii.githubdownloader.domain.repository.RepositoryInfoRepository
import com.arkadii.githubdownloader.domain.usecases.DownloadRepositoryByUrl
import com.arkadii.githubdownloader.domain.usecases.GetDownloadUrl
import com.arkadii.githubdownloader.domain.usecases.GetRepositoryListByUserUseCase
import com.arkadii.githubdownloader.domain.usecases.RepositoryUseCases
import com.arkadii.githubdownloader.util.Constants.GITHUB_BASE_API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
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
    fun provideMyDownloadManagerInstance(
        @ApplicationContext context: Context
    ): MyDownloadManager {
        return MyDownloadManagerImpl(context)
    }

    @Provides
    @Singleton
    fun provideDownloadRepository(
        myDownloadManager: MyDownloadManager
    ): DownloadRepository {
        return DownloadRepositoryImpl(myDownloadManager)
    }

    @Provides
    @Singleton
    fun provideRepositoryUseCases(
        repositoryInfoRepository: RepositoryInfoRepository,
        downloadRepository: DownloadRepository

    ): RepositoryUseCases {
        return RepositoryUseCases(
            getRepositoryListByUserUseCase = GetRepositoryListByUserUseCase(
                repositoryInfoRepository
            ),
            getDownloadUrl = GetDownloadUrl(repositoryInfoRepository),
            downloadRepositoryByUrl = DownloadRepositoryByUrl(downloadRepository)
        )
    }
}