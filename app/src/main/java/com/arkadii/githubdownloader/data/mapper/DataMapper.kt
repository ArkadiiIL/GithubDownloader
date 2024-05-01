package com.arkadii.githubdownloader.data.mapper

import com.arkadii.githubdownloader.data.api.dto.RepositoryInfoDto
import com.arkadii.githubdownloader.data.local.entity.RepositoryInfoEntity
import com.arkadii.githubdownloader.domain.model.RepositoryInfo

object DataMapper {
    fun mapDtoToModel(repositoryInfoDto: RepositoryInfoDto) = RepositoryInfo(
        id = repositoryInfoDto.id,
        name = repositoryInfoDto.name,
        htmlUrl = repositoryInfoDto.htmlUrl,
        description = repositoryInfoDto.description,
        ownerLogin = repositoryInfoDto.owner.login
    )

    fun mapEntityToModel(repositoryInfoEntity: RepositoryInfoEntity) = RepositoryInfo(
        id = repositoryInfoEntity.id,
        name = repositoryInfoEntity.name,
        htmlUrl = repositoryInfoEntity.htmlUrl,
        description = repositoryInfoEntity.description,
        ownerLogin = repositoryInfoEntity.ownerLogin
    )

    fun mapModelToEntity(repositoryInfo: RepositoryInfo) = RepositoryInfoEntity(
        id = repositoryInfo.id,
        name =repositoryInfo.name,
        htmlUrl = repositoryInfo.htmlUrl,
        description = repositoryInfo.description,
        ownerLogin = repositoryInfo.ownerLogin
    )
}