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
        route = Home.tab.route,
        startDestination = Home.Top.route
    ) {
        composable(Home.Top.route) {
            FirstScreen(onClick = { navController.navigate(Home.Next.route) })
        }
        composable(Home.Next.route) { NextScreen() }
    }
}

fun NavGraphBuilder.listsGraph(navController: NavHostController) {
    navigation(
        route = Lists.tab.route,
        startDestination = Lists.Top.route
    ) {
        composable(Lists.Top.route) {
            FourthScreen(
                onClick = { memoId ->
                    navigateToMemoDetail(navController, memoId)
                }
            )
        }

        // "xx/{oo}"の{}を忘れずに！
        composable(
            "${Lists.Detail.route}/{${Lists.MEMO_ID_KEY}}",
            arguments = listOf(
                navArgument(Lists.MEMO_ID_KEY) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry: NavBackStackEntry ->
            // 参考：https://github.com/android/compose-samples/blob/main/Owl/app/src/main/java/com/example/owl/ui/NavGraph.kt
            val arguments = requireNotNull(backStackEntry.arguments)
            val memoId = arguments.getInt(Lists.MEMO_ID_KEY)
            MemoDetailScreen(memoId = memoId)
        }
    }
}

private fun navigateToMemoDetail(
    navController: NavHostController,
    memoId: Int
) {
    navController.navigate("${Lists.Detail.route}/${memoId}")
}
