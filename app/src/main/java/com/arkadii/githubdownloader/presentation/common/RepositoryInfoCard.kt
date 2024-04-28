package com.arkadii.githubdownloader.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.arkadii.githubdownloader.domain.model.Owner
import com.arkadii.githubdownloader.domain.model.RepositoryInfo
import com.arkadii.githubdownloader.presentation.Dimens.ButtonSize1
import com.arkadii.githubdownloader.presentation.Dimens.ExtraSmallPadding1
import com.arkadii.githubdownloader.presentation.Dimens.SmallPadding1
import com.arkadii.githubdownloader.ui.theme.GitHubDownloaderTheme

@Composable
fun RepositoryInfoCard(
    modifier: Modifier = Modifier,
    repositoryInfo: RepositoryInfo,
    onTitleClick: (String) -> Unit
) {
    ElevatedCard(
    ) {
        Row {
            Column(
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = SmallPadding1)
                        .clickable { onTitleClick(repositoryInfo.htmlUrl) },
                    text = repositoryInfo.fullName,
                    style = MaterialTheme.typography.titleSmall.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(ExtraSmallPadding1))
                Text(
                    text = repositoryInfo.description,
                    style = MaterialTheme.typography.bodySmall.copy(),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(ExtraSmallPadding1))
                Text(
                    text = repositoryInfo.owner.login,
                    style = MaterialTheme.typography.labelSmall.copy(),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(ExtraSmallPadding1))
            LoadingButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(ButtonSize1),
                onClick = {}
            )
            Spacer(modifier = Modifier.width(ExtraSmallPadding1))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryInfoCardPreview() {
    GitHubDownloaderTheme {
        RepositoryInfoCard(
            onTitleClick = {},
            repositoryInfo = RepositoryInfo(
                id = 1,
                fullName = "RepositoryInfo_test_fullName",
                htmlUrl = "RepositoryInfo_test_htmlUrl",
                description = "RepositoryInfo_test_description",
                downloadsUrl = "RepositoryInfo_test_downloadsUrl",
                owner = Owner(
                    id = 1,
                    login = "Owner_test_login",
                    avatarUrl = "Owner_test_avatarUrl",
                    url = "Owner_test_url"
                )
            )
        )
    }
}