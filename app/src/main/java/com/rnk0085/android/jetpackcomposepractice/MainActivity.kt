package com.rnk0085.android.jetpackcomposepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposePracticeTheme {
        Greeting("Android")
    }
}

/**
 * 参考：https://techbooster.org/android/ui/18656/
 */
sealed class Item(var dist: String, var icon: ImageVector) {
    object Home : Item("Home", Icons.Filled.Home)
    object Email : Item("Email", Icons.Filled.Email)
    object Stars : Item("Stars", Icons.Filled.Star)
    object Lists : Item("Lists", Icons.Filled.List)
}

@Composable
fun MultipleItemsBottomNavigation() {
    var selectedItem = remember { mutableStateOf(0) }
    val items = listOf(Item.Home, Item.Email, Item.Stars, Item.Lists)

    BottomNavigation {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.dist) },
                label = { Text(item.dist) },
                alwaysShowLabel = false, // 4つ以上のItemのとき
                selected = selectedItem.value == index,
                onClick = { selectedItem.value = index }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview2() {
    JetpackComposePracticeTheme {
        MultipleItemsBottomNavigation()
    }
}
