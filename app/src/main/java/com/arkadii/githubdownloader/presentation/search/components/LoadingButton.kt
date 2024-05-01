package com.arkadii.githubdownloader.presentation.search.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkadii.githubdownloader.presentation.Dimens
import com.arkadii.githubdownloader.presentation.Dimens.BorderStrokeSize1
import com.arkadii.githubdownloader.ui.theme.GitHubDownloaderTheme

@Composable
fun LoadingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(BorderStrokeSize1, MaterialTheme.colorScheme.onBackground),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults
            .outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onBackground)
    ) {
        Icon(
            Icons.Filled.Download,
            contentDescription = null,
            modifier = Modifier.size(Dimens.IconSize1),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingButtonPreview() {
    GitHubDownloaderTheme {
        LoadingButton(onClick = {})
    }
}