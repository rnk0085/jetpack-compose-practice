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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePracticeTheme {
                val navController = rememberNavController()
                MyNavHost(navController = navController)
            }
        }
    }
}

@Composable
fun MyNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.First.route
    ) {
        composable(Routes.First.route) {
            First(
                onButtonClick = {
                    navController.navigate(Routes.Second.route)
                }
            )
        }

        composable(Routes.Second.route) {
            Second(
                onButtonClick = {
                    navController.navigate(Routes.Third.route)
                }
            )
        }

        composable(Routes.Third.route) {
            Third(
                onButtonClick = {
                    navController.navigate(Routes.Fourth.route)
                }
            )
        }

        composable(Routes.Fourth.route) {
            Fourth(
                onButtonClick = {}
            )
        }
    }
}

sealed class Routes(val route: String) {
    object First : Routes("first")
    object Second : Routes("second")
    object Third : Routes("third")
    object Fourth : Routes("fourth")
}

@Composable
fun Screen(name: String, onButtonClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
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
fun First(
    onButtonClick: () -> Unit
) {
    Screen(
        name = "First",
        onButtonClick = onButtonClick
    )
}

@Composable
fun Second(
    onButtonClick: () -> Unit
) {
    Screen(
        name = "Second",
        onButtonClick = onButtonClick
    )
}

@Composable
fun Third(
    onButtonClick: () -> Unit
) {
    Screen(
        name = "Third",
        onButtonClick = onButtonClick
    )
}

@Composable
fun Fourth(
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Fourth")
        Button(onClick = onButtonClick) {
            Text(text = "Back")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun DefaultPreview() {
    JetpackComposePracticeTheme {
        First(
            onButtonClick = {}
        )
    }
}
