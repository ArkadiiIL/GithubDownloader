package com.arkadii.githubdownloader.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arkadii.githubdownloader.data.local.entity.RepositoryInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepositoryInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepository(repository: RepositoryInfoEntity)

    @Query("SELECT * FROM ${DownloadedRepositoryDatabase.TABLE_NAME}")
    fun getAllRepositories(): Flow<List<RepositoryInfoEntity>>
}