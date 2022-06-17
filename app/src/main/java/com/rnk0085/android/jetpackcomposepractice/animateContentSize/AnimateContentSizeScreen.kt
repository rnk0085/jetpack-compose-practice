package com.rnk0085.android.jetpackcomposepractice.animateContentSize

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rnk0085.android.jetpackcomposepractice.MyBox
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

@Composable
fun AnimateContentSizeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "animateContentSize")
                }
            )
        }
    ) {
        var isExpanded by remember { mutableStateOf(false) }

        Button(
            onClick = { isExpanded = !isExpanded },
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        ) {
            Text(text = if (isExpanded) "SHRINK" else "EXPAND")
        }

        Column(
            modifier = Modifier
                .padding(top = 48.dp)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "アニメーション無し")
            NoAnimation(isExpanded = isExpanded)

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Text(text = "アニメーション有り")
            AnimationAvailable(isExpanded = isExpanded)
        }
    }
}

@Composable
private fun AnimationAvailable(isExpanded: Boolean) {
    Column(
        modifier = Modifier.height(128.dp).animateContentSize()
    ) {
        if (isExpanded) MyBox()
        else SmallBox()
    }
}

@Composable
private fun SmallBox() {
    Box(modifier = Modifier.size(64.dp).background(Color.Blue))
}

@Composable
private fun NoAnimation(isExpanded: Boolean) {
    Column(modifier = Modifier.height(128.dp)) {
        if (isExpanded) MyBox()
        else SmallBox()
    }
}

@Preview(showBackground = true, name = "AnimateContentSizeScreenPreview")
@Composable
private fun AnimateContentSizeScreenPreview() {
    JetpackComposePracticeTheme {
        AnimateContentSizeScreen()
    }
}
