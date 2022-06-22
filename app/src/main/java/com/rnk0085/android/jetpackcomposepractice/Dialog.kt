package com.rnk0085.android.jetpackcomposepractice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

/**
 * 参考: https://developer.android.com/reference/kotlin/androidx/compose/ui/window/package-summary#Dialog(kotlin.Function0,androidx.compose.ui.window.DialogProperties,kotlin.Function0)
 */
@Composable
fun MyDialog(
    openDialog: Boolean,
    onDismissRequest: () -> Unit
) {
    val dialogWidth = 300.dp
    val dialogHeight = 400.dp

    if (openDialog) {
        Dialog(onDismissRequest = onDismissRequest) {
            Column(
                modifier = Modifier
                    .size(width = dialogWidth, height = dialogHeight)
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Dialog")
                Button(onClick = onDismissRequest) {
                    Text(text = "閉じる")
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "MyDialogPreview")
@Composable
private fun MyDialogPreview() {
    JetpackComposePracticeTheme {
        MyDialog(
            openDialog = true,
            onDismissRequest = {}
        )
    }
}
