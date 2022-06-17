package com.rnk0085.android.jetpackcomposepractice.rememberInfiniteTransition

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rnk0085.android.jetpackcomposepractice.MyBox
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

@Composable
fun RememberInfiniteTransition() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "RememberInfiniteTransition")
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            AnimationAvailable1()
            AnimationAvailable2()
            AnimationAvailable3()
        }
    }
}

@Composable
private fun AnimationAvailable1() {
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Column {
        Text(text = "LinearEasing / RepeatMode.Reverse")
        MyBox(backgroundColor = color, modifier = Modifier.padding(bottom = 16.dp))
    }
}

@Composable
private fun AnimationAvailable2() {
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 3000
                Color.Yellow at 1500
                Color.Green at 2000
            },
            repeatMode = RepeatMode.Reverse
        )
    )
    Column {
        Text(text = "+ keyframes(Yellow, Green)")
        MyBox(backgroundColor = color, modifier = Modifier.padding(bottom = 16.dp))
    }
}

@Composable
private fun AnimationAvailable3() {
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    Column {
        Text(text = "FastOutSlowInEasing / RepeatMode.Restart")
        MyBox(backgroundColor = color, modifier = Modifier.padding(bottom = 16.dp))
    }
}

@Preview(showBackground = true, name = "RememberInfiniteTransitionPreview")
@Composable
private fun RememberInfiniteTransitionPreview() {
    JetpackComposePracticeTheme {
        RememberInfiniteTransition()
    }
}
