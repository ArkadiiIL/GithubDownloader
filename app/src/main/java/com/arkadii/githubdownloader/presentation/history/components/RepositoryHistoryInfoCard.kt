package com.arkadii.githubdownloader.presentation.history.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.arkadii.githubdownloader.domain.model.RepositoryInfo
import com.arkadii.githubdownloader.presentation.Dimens
import com.arkadii.githubdownloader.ui.theme.GitHubDownloaderTheme

@Composable
fun RepositoryInfoHistoryCard(repositoryInfo: RepositoryInfo) {
    val uriHandler = LocalUriHandler.current
    ElevatedCard(
        modifier = Modifier
            .height(Dimens.CardHeightSize1)
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Text(
                modifier = Modifier
                    .padding(top = Dimens.SmallPadding1)
                    .clickable { uriHandler.openUri(repositoryInfo.htmlUrl) },
                text = repositoryInfo.name,
                style = MaterialTheme.typography.titleSmall.copy(
                    textDecoration = TextDecoration.Underline
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding1))
            Text(
                text = repositoryInfo.description ?: "",
                style = MaterialTheme.typography.bodySmall.copy(),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding1))
            Text(
                text = repositoryInfo.ownerLogin,
                style = MaterialTheme.typography.labelSmall.copy(),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )


        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryInfoHistoryCardPreview() {
    GitHubDownloaderTheme {
        RepositoryInfoHistoryCard(
            repositoryInfo = RepositoryInfo(
                id = 1,
                name = "RepositoryInfo_test_fullName",
                htmlUrl = "RepositoryInfo_test_htmlUrl",
                description = "RepositoryInfo_test_description",
                ownerLogin = "Owner_test_login"
            )
        )
    }
}