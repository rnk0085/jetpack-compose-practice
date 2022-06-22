package com.rnk0085.android.jetpackcomposepractice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePracticeTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    SideEffect {
        println("test: MyApp")
    }

    // TODO: ViewModelを使用
    var openDialog by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "0~9の数字を入力してください")

        TextField(
            value = text,
            onValueChange = { text = it }
        )

        fun checkText(inputText: String) {
            Log.d("test", "checkText")
            inputText.toIntOrNull()?.let { number ->
                if (number < 0) throw NegativeNumberException()
                if (number > 9) throw OverNumberException()
            } ?: throw NotNumberException()
        }


        fun isValidNumber(inputText: String) : Boolean {
            Log.d("test", "isValidNumber")
            return try {
                checkText(inputText)
                Log.d("test", "isValidNumber: checkTextDone")
                true
            } catch (e: MyException) {
                Log.d("test", "isValidNumber: catch Exception")
                errorMessage = when (e) {
                    is NotNumberException -> "数字を入力してください"
                    is NegativeNumberException -> "正の数を入力してください"
                    is OverNumberException -> "9までの数字を入力してください"
                }
                Log.d("test", "errorMessage: $errorMessage")
                false
            }
        }

        Button(onClick = {
            if (!isValidNumber(text)) openDialog = true
        }) {
            Text(text = "決定")
        }
    }

    if (openDialog) {
        MyDialog(
            errorMessage = errorMessage,
            openDialog = openDialog,
            onDismissRequest = { openDialog = false }
        )
    }
}

@Preview(showBackground = true, name = "MyAppPreview")
@Composable
fun MyAppPreview() {
    JetpackComposePracticeTheme {
        MyApp()
    }
}
