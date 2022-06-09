package com.rnk0085.android.jetpackcomposepractice

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

@Composable
fun FirstScreen() {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "FirstScreen")
            }
        }
    ) {
        Text(text = "最初の画面")
    }
}

@Preview(
    showBackground = true,
    name = "FirstScreenPreview"
)
@Composable
fun FirstScreenPreview() {
    JetpackComposePracticeTheme {
        FirstScreen()
    }
}
