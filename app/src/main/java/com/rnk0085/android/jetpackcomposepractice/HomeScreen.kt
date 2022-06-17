package com.rnk0085.android.jetpackcomposepractice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onClick1: () -> Unit = {},
    onClick2: () -> Unit = {},
    onClick3: () -> Unit = {},
    onClick4: () -> Unit = {},
    onClick5: () -> Unit = {},
    onClick6: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onClick1) {
            Text(text = "animate*AsState")
        }

        Button(onClick = onClick2) {
            Text(text = "updateTransition")
        }

        Button(onClick = onClick3) {
            Text(text = "AnimatedVisibility")
        }

        Button(onClick = onClick4) {
            Text(text = "animateContentSize")
        }

        Button(onClick = onClick5) {
            Text(text = "CrossFade")
        }

        Button(onClick = onClick6) {
            Text(text = "RememberInfiniteTransition")
        }
    }
}
