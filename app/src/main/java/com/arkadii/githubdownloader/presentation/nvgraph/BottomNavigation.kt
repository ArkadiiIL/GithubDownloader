package com.arkadii.githubdownloader.presentation.nvgraph

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DataArray
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arkadii.githubdownloader.R
import com.arkadii.githubdownloader.presentation.Dimens.ExtraSmallPadding1
import com.arkadii.githubdownloader.presentation.Dimens.IconSize1
import com.arkadii.githubdownloader.presentation.Dimens.TonalElevationSize1
import com.arkadii.githubdownloader.ui.theme.GitHubDownloaderTheme

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavigationItem(
            Icons.Default.Search,
            text = stringResource(R.string.search_button_navigation_text)
        ),
        BottomNavigationItem(
            Icons.Default.DataArray,
            text = stringResource(R.string.history_button_navigation_text)
        )
    )

    val selectedItem = remember { mutableIntStateOf(0) }

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = TonalElevationSize1
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem.intValue,
                onClick = {
                    selectedItem.intValue = index
                    if (index == 0) {
                        navigateOnScree(navController, Route.SearchScreen.route)
                    } else {
                        navigateOnScree(navController, Route.HistoryScreen.route)
                    }
                },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            item.icon,
                            contentDescription = null,
                            modifier = Modifier.size(IconSize1),
                        )
                        Spacer(modifier = Modifier.height(ExtraSmallPadding1))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(),
            )
        }
    }
}

fun navigateOnScree(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screenRoute ->
            popUpTo(screenRoute) {
                saveState = true
            }
        }
    }
}

data class BottomNavigationItem(
    val icon: ImageVector,
    val text: String
)

@Preview
@Composable
fun NewsBottomNavigationPreview() {
    GitHubDownloaderTheme {
        BottomNavigation(navController = NavController(LocalContext.current))
    }
}