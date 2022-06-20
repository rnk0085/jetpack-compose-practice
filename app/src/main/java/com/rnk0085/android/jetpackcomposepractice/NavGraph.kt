package com.rnk0085.android.jetpackcomposepractice

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rnk0085.android.jetpackcomposepractice.screen.FirstScreen
import com.rnk0085.android.jetpackcomposepractice.screen.NextScreen

// Homeタブ内のナビゲーションに使う
sealed class HomeRoutes(val route: String) {
    object Home : HomeRoutes("home")
    object Next : HomeRoutes("next")
}

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(
        startDestination = Screen.First.route,
        route = HomeRoutes.Home.route
    ) {
        composable(Screen.First.route) {
            FirstScreen(onClick = { navController.navigate(HomeRoutes.Next.route) })
        }
        composable(HomeRoutes.Next.route) { NextScreen() }
    }
}
