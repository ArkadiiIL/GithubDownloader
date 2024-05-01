package com.arkadii.githubdownloader.data.api.dto

import com.google.gson.annotations.SerializedName

data class OwnerDto(
    @SerializedName("login")
    val login: String
)
