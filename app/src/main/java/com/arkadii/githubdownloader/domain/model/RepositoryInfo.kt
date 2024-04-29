package com.arkadii.githubdownloader.domain.model

data class RepositoryInfo(
    val id: Int,
    val name: String,
    val htmlUrl: String,
    val description: String?,
    val downloadsUrl: String,
    val owner: Owner
)
