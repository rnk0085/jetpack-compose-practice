package com.rnk0085.android.jetpackcomposepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.rnk0085.android.jetpackcomposepractice.screen.FirstScreen
import com.rnk0085.android.jetpackcomposepractice.screen.FourthScreen
import com.rnk0085.android.jetpackcomposepractice.screen.NextScreen
import com.rnk0085.android.jetpackcomposepractice.screen.SecondScreen
import com.rnk0085.android.jetpackcomposepractice.screen.ThirdScreen
import com.rnk0085.android.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val nabController = rememberNavController()
    val viewModel: MainViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    JetpackComposePracticeTheme {
        Scaffold(
            bottomBar = {
                MyBottomNavigation(
                    navController = nabController,
                    uiState = uiState,
                    changeSelectedTab = viewModel::changeSelectedTab
                )
            }
        ) {
            MyNavHost(navController = nabController)
        }
    }
}

//sealed class Screen(val route: String) {
//    object First : Screen("first")
//    object Second : Screen("second")
//    object Third : Screen("third")
//    object Fourth : Screen("fourth")
//}

// 「Screen.values().map { it.route }」を使うために変更
enum class Screen(val route: String) {
    First("first"),
    Second("second"),
    Third("third"),
    Fourth("fourth")
}

@Composable
fun MyBottomNavigation(
    navController: NavController,
    uiState: MainUiState,
    changeSelectedTab: (String) -> Unit
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentDestination = navBackStackEntry?.destination?.route ?: Screen.First.route
        val routes = remember { Screen.values().map { it.route } }

        if (currentDestination in routes) changeSelectedTab(currentDestination)

        uiState.bottomNavigationItemUiState.forEach { navigationItemUiState ->
            BottomNavigationItem(
                icon = { Icon(navigationItemUiState.icon, contentDescription = null) },
                label = { Text(stringResource(navigationItemUiState.labelId)) },
                selected = navigationItemUiState.screen.route == uiState.selectedTab,
                onClick = {
                    navController.navigate(navigationItemUiState.screen.route) {
                        // グラフのスタート地点にポップアップすることで、
                        // ユーザーがアイテムを選択する際にバックスタックに大きな目的地が蓄積されるのを防げる
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState
                        }

                        // 同じアイテムを再選択する際に、同じ目的地が複数コピーされるのを避けられる
                        launchSingleTop = true

                        // 以前に選択したアイテムを再選択する際に、状態を復元することが可能
                        restoreState = true
                    }
                },
                /**
                 * alwaysShowLabelをfalseにすると
                 * 選択した時だけラベルが表示されるようになる
                 */
                // alwaysShowLabel = false

                // TODO: 以下を削除
                // 以下は分かりやすいように色を付けているだけ
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.White
            )
        }
    }
}

@Composable
fun MyNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeRoutes.Home.route
    ) {
        homeGraph(navController)
        composable(Screen.Second.route) { SecondScreen() }
        composable(Screen.Third.route) { ThirdScreen() }
        composable(Screen.Fourth.route) { FourthScreen() }
    }
}

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

@Preview(showBackground = true, name = "MyAppPreview")
@Composable
private fun MyAppPreview() {
    JetpackComposePracticeTheme {
        MyApp()
    }
}
