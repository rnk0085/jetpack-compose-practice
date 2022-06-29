package com.rnk0085.android.jetpackcomposepractice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

/**
 * 参考：https://betterprogramming.pub/android-keyboard-handling-using-jetpack-compose-c478f7afaae0
 */
@Composable
fun KeyboardSample(){
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),

        ) {

        var name by rememberSaveable { mutableStateOf("") }
        val updateName = { _name : String ->
            name = _name
        }

        var amount by rememberSaveable { mutableStateOf("") }
        val updateAmount = { _amount : String ->
            amount = _amount
        }

        TextFieldsToExperiment(
            name = name,
            updateName = updateName,
            amount = amount,
            updateAmount = updateAmount
        )

    }
}

@Composable
fun TextFieldsToExperiment(
    name : String,
    updateName : (String) -> Unit = {},
    amount : String,
    updateAmount : (String) -> Unit = {}
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = updateName ,
            label = { Text("Name") },
            placeholder = { Text(text = "Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        )

        OutlinedTextField(
            value = amount,
            onValueChange = updateAmount ,
            label = { Text("Amount") },
            placeholder = { Text(text = "Amount") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        )

    }
}

@Preview(showBackground = true, name = "TextFieldsToExperimentPreview")
@Composable
fun TextFieldsToExperimentPreview() {
    JetpackComposePracticeTheme {
        TextFieldsToExperiment(
            name = "name",
            amount = "200",
        )
    }
}
