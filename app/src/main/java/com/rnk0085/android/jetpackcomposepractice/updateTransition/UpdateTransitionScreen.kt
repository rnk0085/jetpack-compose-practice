package com.rnk0085.android.jetpackcomposepractice.updateTransition

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

enum class BoxState {
    Collapsed,
    Expanded
}

@Composable
fun UpdateTransitionScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "UpdateTransitionScreen")
                }
            )
        }
    ) {
        var currentState by remember { mutableStateOf(BoxState.Collapsed) }

        val onClick = {
            currentState = if (currentState == BoxState.Expanded) BoxState.Collapsed else BoxState.Expanded
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
            AnimationAvailable2(currentState = currentState)

            Spacer(modifier = Modifier.size(16.dp))
            AnimationAvailable3(currentState = currentState)
        }
    }
}

@Composable
private fun NoAnimation(currentState: BoxState) {
    val boxSize = if (currentState == BoxState.Expanded) 128.dp else 64.dp
    val color = if (currentState == BoxState.Expanded) Color.Blue else Color.Red

    MyBox(boxSize = boxSize, color = color)
}

/**
 * Springを使用するバージョン
 * 縮むときが遅い
 */
@Composable
private fun AnimationAvailable1(currentState: BoxState) {
    val transition = updateTransition(currentState, label = "Transition")

    val boxSize by transition.animateDp(
        transitionSpec = {
             when {
                 // 縮むとき
                 BoxState.Expanded isTransitioningTo BoxState.Collapsed ->
                     spring(stiffness = Spring.StiffnessVeryLow)

                 // 広がるとき
                 else -> spring(stiffness = Spring.StiffnessMedium)
             }
        },
        label = "Box Size"
    ) { state ->
        when (state) {
            BoxState.Collapsed -> 64.dp
            BoxState.Expanded -> 128.dp
        }
    }

    val color by transition.animateColor(
        transitionSpec = {
            when {
                // 縮むとき
                BoxState.Expanded isTransitioningTo BoxState.Collapsed -> spring(stiffness = Spring.StiffnessVeryLow)

                // 広がるとき
                else -> spring(stiffness = Spring.StiffnessMedium)
            }
        },
        label = "Background Color"
    ) { state ->
        when (state) {
            BoxState.Collapsed -> Color.Red
            BoxState.Expanded -> Color.Blue
        }
    }

    MyBox(boxSize = boxSize, color = color)
}

/**
 * Springを自分で数字指定するバージョン
 * 実際は上記①の反対にしてある
 */
@Composable
private fun AnimationAvailable2(currentState: BoxState) {
    val transition = updateTransition(currentState, label = "Transition")

    val boxSize by transition.animateDp(
        transitionSpec = {
            when {
                // 縮むとき
                BoxState.Expanded isTransitioningTo BoxState.Collapsed ->
                    spring(stiffness = 1500f)

                // 広がるとき
                else -> spring(stiffness = 50f)
            }
        },
        label = "Box Size"
    ) { state ->
        when (state) {
            BoxState.Collapsed -> 64.dp
            BoxState.Expanded -> 128.dp
        }
    }

    val color by transition.animateColor(
        transitionSpec = {
            when {
                // 縮むとき
                BoxState.Expanded isTransitioningTo BoxState.Collapsed -> spring(stiffness = 1500f)

                // 広がるとき
                else -> spring(stiffness = 50f)
            }
        },
        label = "Background Color"
    ) { state ->
        when (state) {
            BoxState.Collapsed -> Color.Red
            BoxState.Expanded -> Color.Blue
        }
    }

    MyBox(boxSize = boxSize, color = color)
}

/**
 * 遷移するのにかかる時間(tween)を設定するバージョン
 * 縮むときは2s、広がるときは5s
 * （遷移途中でボタンを押すと素早く戻る）
 */
@Composable
private fun AnimationAvailable3(currentState: BoxState) {
    val transition = updateTransition(currentState, label = "Transition")

    val boxSize by transition.animateDp(
        transitionSpec = {
            when {
                // 縮むとき
                BoxState.Expanded isTransitioningTo BoxState.Collapsed -> tween(durationMillis = 2000)

                // 広がるとき
                else -> tween(durationMillis = 5000)
            }
        },
        label = "Box Size"
    ) { state ->
        when (state) {
            BoxState.Collapsed -> 64.dp
            BoxState.Expanded -> 128.dp
        }
    }

    val color by transition.animateColor(
        transitionSpec = {
            when {
                // 縮むとき
                BoxState.Expanded isTransitioningTo BoxState.Collapsed -> tween(durationMillis = 2000)

                // 広がるとき
                else -> tween(durationMillis = 5000)
            }
        },
        label = "Background Color"
    ) { state ->
        when (state) {
            BoxState.Collapsed -> Color.Red
            BoxState.Expanded -> Color.Blue
        }
    }

    MyBox(boxSize = boxSize, color = color)
}

@Composable
private fun MyBox(boxSize: Dp, color: Color) {
    Column(modifier = Modifier.height(128.dp)) {
        Box(modifier = Modifier
            .size(boxSize)
            .background(color))
    }
}

@Preview(showBackground = true, name = "")
@Composable
private fun UpdateTransitionScreenPreview() {
    JetpackComposePracticeTheme {
        UpdateTransitionScreen()
    }
}
