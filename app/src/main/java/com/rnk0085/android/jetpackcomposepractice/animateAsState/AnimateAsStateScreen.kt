package com.rnk0085.android.jetpackcomposepractice.animateAsState

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

@Composable
fun AnimateAsStateScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "animate*AsState")
                }
            )
        }
    ) {
        var isBlue by remember { mutableStateOf(true) }

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
        ) {
            Button(onClick = { isBlue = !isBlue }) {
                Text(text = "CHANGE COLOR")
            }
            Text(text = "アニメーション無し")
            NoAnimation(isBlue = isBlue)

            Divider()

            Text(text = "アニメーション有り")
            AnimationAvailable(isBlue = isBlue)
        }
    }
}

@Composable
private fun AnimationAvailable(
    isBlue: Boolean
) {
    val backgroundColor by animateColorAsState(if (isBlue) Color.Blue else Color.Red)

    Box(
        modifier = Modifier
            .size(128.dp)
            .background(backgroundColor)
    )
}

@Composable
private fun NoAnimation(
    isBlue: Boolean
) {
    val backgroundColor = if (isBlue) Color.Blue else Color.Red

    Box(
        modifier = Modifier
            .size(128.dp)
            .background(backgroundColor)
    )
}

@Composable
private fun BoxWithButton(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column {
        Button(onClick = onClick) {
            Text(text = "CHANGE COLOR")
        }
        content()
    }
}

@Preview(showBackground = true, name = "AnimateAsStateScreenPreview")
@Composable
private fun AnimateAsStateScreenPreview() {
    JetpackComposePracticeTheme {
        AnimateAsStateScreen()
    }
}

@Preview(showBackground = true, name = "NoAnimationPreview")
@Composable
private fun NoAnimationPreview() {
    JetpackComposePracticeTheme {
        var isBlue by remember { mutableStateOf(true) }
        BoxWithButton(onClick = { isBlue = !isBlue }) {
            NoAnimation(isBlue = isBlue)
        }
    }
}

@Preview(showBackground = true, name = "AnimationAvailablePreview")
@Composable
private fun AnimationAvailablePreview() {
    JetpackComposePracticeTheme {
        var isBlue by remember { mutableStateOf(true) }
        BoxWithButton(onClick = { isBlue = !isBlue }) {
            AnimationAvailable(isBlue = isBlue)
        }
    }
}

