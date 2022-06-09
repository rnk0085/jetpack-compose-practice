package com.rnk0085.android.jetpackcomposepractice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

@Composable
fun FirstScreen(
    onNextButtonClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "FirstScreen")
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(text = "最初の画面")
            Button(onClick = onNextButtonClick) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(
    showBackground = true,
    name = "FirstScreenPreview"
)
@Composable
fun FirstScreenPreview() {
    JetpackComposePracticeTheme {
        FirstScreen(onNextButtonClick = {})
    }
}
