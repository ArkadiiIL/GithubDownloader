package com.arkadii.githubdownloader.data.api.dto

import com.arkadii.githubdownloader.domain.model.Owner
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
    @SerializedName("downloads_url")
    val downloadsUrl: String,
    @SerializedName("owner")
    val owner: OwnerDto
)
