package com.arkadii.githubdownloader.data.api.dto

import com.google.gson.annotations.SerializedName

data class OwnerDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("url")
    val url: String
)
