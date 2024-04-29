package com.arkadii.githubdownloader.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import com.arkadii.githubdownloader.R
import com.arkadii.githubdownloader.presentation.Dimens
import com.arkadii.githubdownloader.ui.theme.GitHubDownloaderTheme
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun ErrorScreen(error: LoadState.Error? = null) {
    val errorText = stringResource(id = parseErrorMessage(error))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Icon(
                    Icons.Filled.Error,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding1))
            Text(
                text = errorText,
                style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray)

            )
        }
    }

}

fun parseErrorMessage(error: LoadState.Error?): Int {
    return when (error?.error) {
        is SocketTimeoutException -> {
            R.string.server_unavailable_error_text
        }

        is ConnectException -> {
            R.string.internet_unavailable_error_text
        }

        else -> {
            R.string.unknown_error_text
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ErrorScreePreview() {
    GitHubDownloaderTheme {
        ErrorScreen()
    }
}