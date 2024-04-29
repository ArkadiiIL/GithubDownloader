package com.arkadii.githubdownloader.presentation.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.arkadii.githubdownloader.R
import com.arkadii.githubdownloader.presentation.Dimens.ButtonSize1
import com.arkadii.githubdownloader.presentation.Dimens.CardHeightSize1
import com.arkadii.githubdownloader.presentation.Dimens.ExtraSmallPadding1
import com.arkadii.githubdownloader.presentation.Dimens.MediumPadding1
import com.arkadii.githubdownloader.presentation.Dimens.ShimmerTextFieldSize1
import com.arkadii.githubdownloader.presentation.Dimens.ShimmerTextFieldSize2
import com.arkadii.githubdownloader.presentation.Dimens.ShimmerTextFieldSize3
import com.arkadii.githubdownloader.presentation.Dimens.SmallPadding1
import com.arkadii.githubdownloader.ui.theme.GitHubDownloaderTheme

@Composable
fun RepositoryInfoCardShimmerEffect(modifier: Modifier = Modifier) {
    ElevatedCard(modifier = modifier
        .height(CardHeightSize1)
        .fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(
                bottom = ExtraSmallPadding1,
                start = ExtraSmallPadding1,
                end = ExtraSmallPadding1
            )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.8f),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(top = SmallPadding1)
                        .height(ShimmerTextFieldSize1)
                        .shimmerEffect()

                )
                Spacer(modifier = Modifier.height(ExtraSmallPadding1))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ShimmerTextFieldSize2)
                        .shimmerEffect()

                )
                Spacer(modifier = Modifier.height(ExtraSmallPadding1))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(ShimmerTextFieldSize3)
                        .shimmerEffect()

                )
            }
            Spacer(modifier = Modifier.width(MediumPadding1))

            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(ButtonSize1)
                    .clip(CircleShape)
                    .shimmerEffect(),
            )
        }
    }
}

fun Modifier.shimmerEffect() = this.then(composed {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
})

@Composable
@Preview(showBackground = true)
fun RepositoryInfoCardShimmerEffectPreview() {
    GitHubDownloaderTheme {
        RepositoryInfoCardShimmerEffect()
    }
}