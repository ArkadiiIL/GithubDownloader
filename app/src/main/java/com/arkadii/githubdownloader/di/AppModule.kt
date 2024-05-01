package com.arkadii.githubdownloader.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.arkadii.githubdownloader.data.api.RepositoryInfoApi
import com.arkadii.githubdownloader.data.downloader.MyDownloadManager
import com.arkadii.githubdownloader.data.downloader.MyDownloadManagerImpl
import com.arkadii.githubdownloader.data.local.DownloadedRepositoryDatabase
import com.arkadii.githubdownloader.data.local.RepositoryInfoDao
import com.arkadii.githubdownloader.data.repository.DownloadRepositoryImpl
import com.arkadii.githubdownloader.data.repository.RepositoryInfoRepositoryImpl
import com.arkadii.githubdownloader.domain.repository.DownloadRepository
import com.arkadii.githubdownloader.domain.repository.RepositoryInfoRepository
import com.arkadii.githubdownloader.domain.usecases.DownloadRepositoryByUrl
import com.arkadii.githubdownloader.domain.usecases.GetAllDownloadedRepositories
import com.arkadii.githubdownloader.domain.usecases.GetRepositoryListByUser
import com.arkadii.githubdownloader.domain.usecases.InsertRepositoryInfo
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
        repositoryInfoApi: RepositoryInfoApi,
        repositoryInfoDao: RepositoryInfoDao
    ): RepositoryInfoRepository {
        return RepositoryInfoRepositoryImpl(
            repositoryInfoApi = repositoryInfoApi,
            repositoryInfoDao = repositoryInfoDao
        )
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
            getRepositoryListByUser = GetRepositoryListByUser(
                repositoryInfoRepository
            ),
            downloadRepositoryByUrl = DownloadRepositoryByUrl(downloadRepository),
            getAllDownloadedRepositories = GetAllDownloadedRepositories(repositoryInfoRepository),
            insertRepositoryInfo = InsertRepositoryInfo(repositoryInfoRepository)
        )
    }

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ): DownloadedRepositoryDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = DownloadedRepositoryDatabase::class.java,
            name = DownloadedRepositoryDatabase.DATABASE_NAME
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideRepositoryInfoDao(
        downloadedRepositoryDatabase: DownloadedRepositoryDatabase
    ): RepositoryInfoDao {
        return downloadedRepositoryDatabase.dao
    }
}