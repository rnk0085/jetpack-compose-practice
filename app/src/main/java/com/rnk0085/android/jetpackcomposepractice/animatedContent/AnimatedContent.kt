package com.rnk0085.android.jetpackcomposepractice.animatedContent

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rnk0085.android.jetpackcomposepractice.animateContentSize.AnimateContentSizeScreen
import com.rnk0085.android.jetpackcomposepractice.crossFade.IconState
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

@Composable
fun AnimatedContentScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "AnimatedContentScreen")
                }
            )
        }
    ) {
        var currentState by remember { mutableStateOf(IconState.Heart) }

        val onClick = {
            currentState = if (currentState == IconState.Heart) IconState.Star else IconState.Heart
        }

        Button(
            onClick = onClick,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        ) {
            Text("CHANGE COLOR AND SIZE")
        }
        Column(
            modifier = Modifier
                .padding(top = 48.dp)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "アニメーション無し")
            NoAnimation(currentState)

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Text(text = "アニメーション有り")

            Text(text = "Default")
            AnimationAvailable1(currentState)

            Spacer(modifier = Modifier.size(16.dp))

            Text(text = "カスタマイズ: SizeTransform = false")
            AnimationAvailable2(currentState)

            Spacer(modifier = Modifier.size(16.dp))

            Text(text = "カスタマイズ: SizeTransform = true")
            AnimationAvailable3(currentState)
        }
    }
}

@Composable
private fun NoAnimation(currentState: IconState) {
    when (currentState) {
        IconState.Heart -> HeartIcon()
        IconState.Star -> StarIcon()
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimationAvailable1(currentState: IconState) {
    AnimatedContent(targetState = currentState) { state ->
        when (state) {
            IconState.Heart -> HeartIcon()
            IconState.Star -> StarIcon()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimationAvailable2(currentState: IconState) {
    AnimatedContent(
        targetState = currentState,
        transitionSpec = {
            when (targetState) {
                IconState.Heart -> {
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                }
                IconState.Star -> {
                    slideInVertically { height -> -height } + fadeIn() with
                            slideOutVertically { height -> height } + fadeOut()
                }
            }.using(
                SizeTransform(clip = false)
            )
        }
    ) { state ->
        when (state) {
            IconState.Heart -> HeartIcon()
            IconState.Star -> StarIcon()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimationAvailable3(currentState: IconState) {
    AnimatedContent(
        targetState = currentState,
        transitionSpec = {
            when (targetState) {
                IconState.Heart -> {
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                }
                IconState.Star -> {
                    slideInVertically { height -> -height } + fadeIn() with
                            slideOutVertically { height -> height } + fadeOut()
                }
            }.using(
                SizeTransform(clip = true)
            )
        }
    ) { state ->
        when (state) {
            IconState.Heart -> HeartIcon()
            IconState.Star -> StarIcon()
        }
    }
}

@Composable
private fun HeartIcon() {
    Icon(
        Icons.Rounded.Favorite,
        tint = Color.Red,
        modifier = Modifier.size(64.dp),
        contentDescription = "heart"
    )
}

@Composable
private fun StarIcon() {
    Icon(
        Icons.Rounded.Star,
        tint = Color.Yellow,
        modifier = Modifier.size(64.dp),
        contentDescription = "star"
    )
}

@Preview(showBackground = true, name = "AnimatedContentScreenPreview")
@Composable
private fun AnimatedContentScreenPreview() {
    JetpackComposePracticeTheme {
        AnimateContentSizeScreen()
    }
}
