package com.rnk0085.android.jetpackcomposepractice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

@Composable
fun FirstScreen(
    onNextButtonClick: (String) -> Unit
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
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "最初の画面", modifier = Modifier.padding(bottom = 8.dp))

            var nameText by remember { mutableStateOf("") }

            val isEmpty = nameText == ""

            OutlinedTextField(
                value = nameText,
                onValueChange = { nameText = it },
                label = { Text(text = "name") },
                isError = isEmpty
            )

            if (isEmpty) {
                Text(
                    text = "名前を入力してください",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption
                )
            }

            Button(
                enabled = !isEmpty,
                onClick = { onNextButtonClick(nameText) },
                modifier = Modifier.padding(top = 8.dp)
            ) {
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
