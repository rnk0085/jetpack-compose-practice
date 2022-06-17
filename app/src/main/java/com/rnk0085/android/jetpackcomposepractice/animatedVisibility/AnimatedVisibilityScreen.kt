package com.rnk0085.android.jetpackcomposepractice.animatedVisibility

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rnk0085.android.jetpackcomposepractice.MyBox
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

@Composable
fun AnimatedVisibilityScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "AnimatedVisibilityScreen")
                }
            )
        }
    ) {
        var isShown by remember { mutableStateOf(true) }
        Button(
            onClick = { isShown = !isShown },
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        ) {
            Text(text = if (isShown) "HIDE" else "SHOW")
        }

        Column(
            modifier = Modifier
                .padding(top = 48.dp)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "アニメーション無し")
            NoAnimation(isShown)

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Text(text = "アニメーション有り")
            AnimationAvailable1(isShown)
            AnimationAvailable2(isShown)
            AnimationAvailable3(isShown)
        }
    }
}

@Composable
private fun NoAnimation(isShown: Boolean) {
    Column(modifier = Modifier.size(128.dp)) {
        if (isShown) MyBox()
    }
}

@Composable
private fun AnimationAvailable1(isShown: Boolean) {
    Column(modifier = Modifier.padding(bottom = 16.dp).size(150.dp)) {
        Text(text = "Default")
        AnimatedVisibility(visible = isShown) {
            MyBox()
        }
    }
}

@Composable
private fun AnimationAvailable2(isShown: Boolean) {
    val density = LocalDensity.current

    Column(modifier = Modifier.padding(bottom = 16.dp).size(150.dp)) {
        Text(text = "カスタマイズ")
        AnimatedVisibility(
            visible = isShown,
            enter = slideInVertically {
                with(density) { 128.dp.roundToPx() }
            } + expandVertically(
                expandFrom = Alignment.Top
            ) + fadeIn(
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            MyBox()
        }
    }
}

@Composable
private fun AnimationAvailable3(isShown: Boolean) {
    val density = LocalDensity.current

    Column(modifier = Modifier.padding(bottom = 16.dp).size(150.dp)) {
        Text(text = "expandIn / shrinkOut")
        AnimatedVisibility(
            visible = isShown,
            enter = expandIn(),
            exit = shrinkOut()
        ) {
            MyBox()
        }
    }
}

@Preview(showBackground = true, name = "AnimatedVisibilityScreenPreview")
@Composable
private fun AnimatedVisibilityScreenPreview() {
    JetpackComposePracticeTheme {
        AnimatedVisibilityScreen()
    }
}
