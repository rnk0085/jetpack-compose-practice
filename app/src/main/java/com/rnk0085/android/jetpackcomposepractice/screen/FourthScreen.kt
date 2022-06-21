package com.rnk0085.android.jetpackcomposepractice.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val memoList = List(20) { Memo(id = it, title = "Title: $it") }

@Composable
fun FourthScreen(
    onClick: (Int) -> Unit
) {
    MyList(memoList = memoList, onClick = onClick)
}

@Composable
fun MyList(
    memoList: List<Memo>,
    onClick: (Int) -> Unit
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(text = "Lists")
            Divider()
        }

        items(memoList) { memo ->
            MemoItem(
                memo = memo,
                onClick = onClick
            )
        }
    }
}

@Composable
fun MemoItem(
    memo: Memo,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable {
                onClick(memo.id)
            }
    ) {
        Text(text = memo.title)
    }
}

@Composable
fun MemoDetailScreen(memoId: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val memo = memoList.find {
            it.id == memoId
        }

        Text(text = "MemoDetail")
        Text(text = memo?.title ?: "メモタイトル")
    }
}

data class Memo(
    val id: Int,
    val title: String,
)
