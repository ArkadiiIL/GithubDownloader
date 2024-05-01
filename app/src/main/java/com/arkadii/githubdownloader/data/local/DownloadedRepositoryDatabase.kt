package com.arkadii.githubdownloader.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arkadii.githubdownloader.data.local.entity.RepositoryInfoEntity

@Database(entities = [RepositoryInfoEntity::class], version = 1)
abstract class DownloadedRepositoryDatabase : RoomDatabase() {
    abstract val dao: RepositoryInfoDao

    companion object {
        const val DATABASE_NAME = "downloaded_db"
        const val TABLE_NAME = "repository"
    }
}