package com.rnk0085.android.jetpackcomposepractice.animateAsState

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rnk0085.android.jetpackcomposepractice.MyBox
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

        Button(
            onClick = { isBlue = !isBlue },
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        ) {
            Text(text = "CHANGE COLOR")
        }

        Column(modifier = Modifier
            .padding(top = 48.dp)
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
        ) {

            Text(text = "アニメーション無し")
            NoAnimation(isBlue = isBlue)

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Text(text = "アニメーション有り")

            AnimationAvailable(isBlue = isBlue)
            AnimationAvailableWithFastOutSlowInEasing(isBlue = isBlue)
            AnimationAvailableWithLinearOutSlowInEasing(isBlue = isBlue)
            AnimationAvailableWithFastOutLinearInEasing(isBlue = isBlue)
            AnimationAvailableWithLinearEasing(isBlue = isBlue)
        }
    }
}

@Composable
private fun AnimationAvailable(isBlue: Boolean) {
    val backgroundColor by animateColorAsState(if (isBlue) Color.Blue else Color.Red)

    Column {
        Text(text = "Default")
        MyBox(backgroundColor = backgroundColor)
    }
}

@Composable
private fun AnimationAvailableWithFastOutSlowInEasing(isBlue: Boolean) {
    val backgroundColor by animateColorAsState(
        if (isBlue) Color.Blue else Color.Red,
        animationSpec = tween(
            durationMillis = 3000,
            easing = FastOutSlowInEasing
        )
    )
    Column {
        Text(text = "FastOutSlowInEasing")
        MyBox(backgroundColor = backgroundColor)
    }
}

@Composable
private fun AnimationAvailableWithLinearOutSlowInEasing(isBlue: Boolean) {
    val backgroundColor by animateColorAsState(
        if (isBlue) Color.Blue else Color.Red,
        animationSpec = tween(
            durationMillis = 3000,
            easing = LinearOutSlowInEasing
        )
    )
    Column {
        Text(text = "LinearOutSlowInEasing")
        MyBox(backgroundColor = backgroundColor)
    }
}

@Composable
private fun AnimationAvailableWithFastOutLinearInEasing(isBlue: Boolean) {
    val backgroundColor by animateColorAsState(
        if (isBlue) Color.Blue else Color.Red,
        animationSpec = tween(
            durationMillis = 3000,
            easing = FastOutLinearInEasing
        )
    )
    Column {
        Text(text = "FastOutLinearInEasing")
        MyBox(backgroundColor = backgroundColor)
    }
}

@Composable
private fun AnimationAvailableWithLinearEasing(isBlue: Boolean) {
    val backgroundColor by animateColorAsState(
        if (isBlue) Color.Blue else Color.Red,
        animationSpec = tween(
            durationMillis = 3000,
            easing = LinearEasing
        )
    )
    Column {
        Text(text = "LinearEasing")
        MyBox(backgroundColor = backgroundColor)
    }
}

@Composable
private fun NoAnimation(isBlue: Boolean) {
    val backgroundColor = if (isBlue) Color.Blue else Color.Red
    MyBox(backgroundColor = backgroundColor)
}

@Preview(showBackground = true, name = "AnimateAsStateScreenPreview")
@Composable
private fun AnimateAsStateScreenPreview() {
    JetpackComposePracticeTheme {
        AnimateAsStateScreen()
    }
}
