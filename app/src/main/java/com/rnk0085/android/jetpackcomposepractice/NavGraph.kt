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

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(
        route = "home",
        startDestination = "home/top"
    ) {
        composable("home/top") {
            FirstScreen(onClick = { navController.navigate("home/next") })
        }
        composable("home/next") { NextScreen() }
    }
}

fun NavGraphBuilder.listsGraph(navController: NavHostController) {
    navigation(
        route = "lists",
        startDestination = "lists/top"
    ) {
        composable("lists/top") {
            FourthScreen(
                onClick = { memoId ->
                    navigateToMemoDetail(navController, memoId)
                }
            )
        }
        composable(
            "lists/detail/{memoId}",
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
    navController.navigate("lists/detail/${memoId}")
}
