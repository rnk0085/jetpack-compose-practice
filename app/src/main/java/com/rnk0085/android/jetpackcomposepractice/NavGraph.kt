package com.rnk0085.android.jetpackcomposepractice

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.rnk0085.android.jetpackcomposepractice.screen.FirstScreen
import com.rnk0085.android.jetpackcomposepractice.screen.FourthScreen
import com.rnk0085.android.jetpackcomposepractice.screen.MemoDetailScreen
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

sealed class ListRoutes(val route: String) {
    object Lists : ListRoutes("lists")
    object Detail : ListRoutes("detail")
}

fun NavGraphBuilder.listsGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.Fourth.route,
        route = ListRoutes.Lists.route
    ) {
        composable(Screen.Fourth.route) {
            FourthScreen(
                onClick = { memoId ->
                    navigateToMemoDetail(navController, memoId)
                }
            )
        }
        composable(
            "${ListRoutes.Detail.route}/{memoId}",
            arguments = listOf(
                navArgument("memoId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry: NavBackStackEntry ->
            // 参考：https://github.com/android/compose-samples/blob/main/Owl/app/src/main/java/com/example/owl/ui/NavGraph.kt
            val arguments = requireNotNull(backStackEntry.arguments)
            val memoId = arguments.getInt("memoId")
            MemoDetailScreen(memoId = memoId)
        }
    }
}

private fun navigateToMemoDetail(
    navController: NavHostController,
    memoId: Int
) {
    navController.navigate("${ListRoutes.Detail.route}/${memoId}")
}
