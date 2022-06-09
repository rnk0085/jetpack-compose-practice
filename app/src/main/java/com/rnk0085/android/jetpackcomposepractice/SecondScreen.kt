package com.rnk0085.android.jetpackcomposepractice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

@Composable
fun SecondScreen(
    name: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "SecondScreen")
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "次の画面", modifier = Modifier.padding(bottom = 8.dp))

            Row {
                Text(text = "name: ")
                Text(text = name)
            }
        }
    }
}

@Preview(
    showBackground = true,
    name = "SecondScreenPreview"
)
@Composable
fun SecondScreenPreview() {
    JetpackComposePracticeTheme {
        SecondScreen("Rin")
    }
}

