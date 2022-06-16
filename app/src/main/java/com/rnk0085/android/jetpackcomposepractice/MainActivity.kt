package com.rnk0085.android.jetpackcomposepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

/**
 * 関連リンク
 * 問題提起：https://zenn.dev/ko2ic/articles/3601dea3d35013
 * 解決策（Notion）：https://www.notion.so/2022-6-16-8a33605ffa5c4d10907bcb909b76c2af
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePracticeTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
) {
    val count: Int by viewModel.count.collectAsState()
    SideEffect { println("MainScreen") }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ACompose(
            count = count
        )
        BCompose()

        /**
         * <After>
         * Cボタンを押してもC＆Dは再コンポジションされなくなった！！
         */
        CCompose(
            onClick = viewModel::increaseCount
        )

        DCompose(
            onClick = viewModel::resetCount
        )


        /**
         * <Before>
         * Cボタンを押しただけで、C＆Dが再コンポジションされる
         *  -> 本来であれば、CとDは何も変更が無いので再コンポジションはしないで欲しい
         */
        /*
        CCompose {
            viewModel.increaseCount()
        }

        DCompose {
            viewModel.resetCount()
        }
         */
    }
}


@Composable
private fun ACompose(count: Int) {
    SideEffect { println("ACompose") }
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "$count"
        )
        ChildACompose()
    }
}

@Composable
private fun ChildACompose() {
    SideEffect { println("ChildACompose") }
    Text(
        text = "ChildACompose"
    )
}

@Composable
private fun BCompose() {
    SideEffect { println("BCompose") }
    Text(
        text = "I am composable that will not be recompose"
    )
}

@Composable
private fun CCompose(onClick: () -> Unit) {
    SideEffect { println("CCompose") }
    Button(onClick = {
        onClick()
    }) {
        Icon(Icons.Outlined.Add, contentDescription = "+")
    }
}

@Composable
private fun DCompose(onClick: () -> Unit) {
    SideEffect { println("DCompose") }

    Button(onClick = {
        onClick()
    }) {
        Icon(Icons.Outlined.Refresh, contentDescription = "+")
    }
}
