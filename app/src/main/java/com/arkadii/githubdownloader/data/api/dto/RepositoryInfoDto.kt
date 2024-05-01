package com.arkadii.githubdownloader.data.api.dto

import com.google.gson.annotations.SerializedName

data class RepositoryInfoDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("owner")
    val owner: OwnerDto
)
