package com.rnk0085.android.jetpackcomposepractice

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

@Composable
fun SecondScreen() {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "SecondScreen")
            }
        }
    ) {
        Text(text = "次の画面")
    }
}

@Preview(
    showBackground = true,
    name = "SecondScreenPreview"
)
@Composable
fun SecondScreenPreview() {
    JetpackComposePracticeTheme {
        SecondScreen()
    }
}

