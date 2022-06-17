package com.rnk0085.android.jetpackcomposepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rnk0085.android.jetpackcomposepractice.animateAsState.AnimateAsStateScreen
import com.rnk0085.android.jetpackcomposepractice.animateContentSize.AnimateContentSizeScreen
import com.rnk0085.android.jetpackcomposepractice.animatedVisibility.AnimatedVisibilityScreen
import com.rnk0085.android.jetpackcomposepractice.crossFade.CrossFadeScreen
import com.rnk0085.android.jetpackcomposepractice.rememberInfiniteTransition.RememberInfiniteTransition
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme
import com.rnk0085.android.jetpackcomposepractice.updateTransition.UpdateTransitionScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePracticeTheme {
                val navController = rememberNavController()
                MainNavHost(navController = navController)
            }
        }
    }
}

@Composable
fun MainNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(Routes.Home.route) {
            HomeScreen(
                onClick1 = {
                    navController.navigate(Routes.AnimateAsState.route)
                },
                onClick2 = {
                    navController.navigate(Routes.UpdateTransition.route)
                },
                onClick3 = {
                    navController.navigate(Routes.AnimatedVisibility.route)
                },
                onClick4 = {
                    navController.navigate(Routes.AnimateContentSize.route)
                },
                onClick5 = {
                    navController.navigate(Routes.CrossFade.route)
                },
                onClick6 = {
                    navController.navigate(Routes.RememberInfiniteTransition.route)
                }
            )
        }
        composable(Routes.AnimateAsState.route) {
            AnimateAsStateScreen()
        }

        composable(Routes.UpdateTransition.route) {
            UpdateTransitionScreen()
        }

        composable(Routes.AnimatedVisibility.route) {
            AnimatedVisibilityScreen()
        }

        composable(Routes.AnimateContentSize.route) {
            AnimateContentSizeScreen()
        }

        composable(Routes.CrossFade.route) {
            CrossFadeScreen()
        }

        composable(Routes.RememberInfiniteTransition.route) {
            RememberInfiniteTransition()
        }
    }
}

@Composable
internal fun MyBox(backgroundColor: Color = Color.Blue) {
    Box(
        modifier = Modifier
            .size(128.dp)
            .background(backgroundColor)
    )
}

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object AnimateAsState : Routes("animateAsState")
    object UpdateTransition : Routes("updateTransition")
    object AnimatedVisibility : Routes("animatedVisibility")
    object AnimateContentSize : Routes("animateConteSize")
    object CrossFade : Routes("crossFade")
    object RememberInfiniteTransition : Routes("rememberInfiniteTransition")
}
