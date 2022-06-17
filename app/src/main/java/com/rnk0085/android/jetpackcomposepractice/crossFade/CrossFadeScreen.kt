package com.rnk0085.android.jetpackcomposepractice.crossFade

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
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
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

enum class IconState {
    Heart,
    Star
}

@Composable
fun CrossFadeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "CrossFadeScreen")
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
            AnimationAvailable1(currentState)
            Spacer(modifier = Modifier.size(16.dp))
            AnimationAvailable2(currentState)
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

@Composable
private fun AnimationAvailable1(currentState: IconState) {
    Crossfade(targetState = currentState) { state ->
        when (state) {
            IconState.Heart -> HeartIcon()
            IconState.Star -> StarIcon()
        }
    }
}

@Composable
private fun AnimationAvailable2(currentState: IconState) {
    Crossfade(
        targetState = currentState,
        animationSpec = tween(
            durationMillis = 3000
        )
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

@Preview(showBackground = true, name = "CrossFadeScreenPreview")
@Composable
private fun CrossFadeScreenPreview() {
    JetpackComposePracticeTheme {
        CrossFadeScreen()
    }
}
