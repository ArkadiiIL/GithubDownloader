package com.arkadii.githubdownloader.data.api.mapper

import com.arkadii.githubdownloader.data.api.dto.RepositoryInfoDto
import com.arkadii.githubdownloader.domain.model.Owner
import com.arkadii.githubdownloader.domain.model.RepositoryInfo

object ApiMapper {
    fun mapDtoToModel(repositoryInfoDto: RepositoryInfoDto) = RepositoryInfo(
        id = repositoryInfoDto.id,
        fullName = repositoryInfoDto.fullName,
        htmlUrl = repositoryInfoDto.htmlUrl,
        description = repositoryInfoDto.description,
        downloadsUrl = repositoryInfoDto.downloadsUrl,
        owner = Owner(
            id = repositoryInfoDto.owner.id,
            login = repositoryInfoDto.owner.login,
            avatarUrl = repositoryInfoDto.owner.avatarUrl,
            url = repositoryInfoDto.owner.url
        )
    )
}