package com.rnk0085.android.jetpackcomposepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePracticeTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    First()
                }
            }
        }
    }
}

@Composable
fun Screen(name: String, onButtonClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = name)
        Button(onClick = onButtonClick) {
            Text(text = "Next")
        }
    }
}

@Composable
fun First() {
    Screen(
        name = "First",
        onButtonClick = {}
    )
}

@Composable
fun Second() {
    Screen(
        name = "Second",
        onButtonClick = {}
    )
}

@Composable
fun Third() {
    Screen(
        name = "Third",
        onButtonClick = {}
    )
}

@Composable
fun Fourth() {
    Screen(
        name = "Fourth",
        onButtonClick = {}
    )
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun DefaultPreview() {
    JetpackComposePracticeTheme {
        First()
    }
}
