package com.arkadii.githubdownloader.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arkadii.githubdownloader.data.local.DownloadedRepositoryDatabase

@Entity(tableName = DownloadedRepositoryDatabase.TABLE_NAME)
data class RepositoryInfoEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val htmlUrl: String,
    val description: String?,
    val ownerLogin: String
)
