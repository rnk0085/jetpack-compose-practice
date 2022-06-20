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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rnk0085.android.jetpackcomposepractice.screen.FirstScreen
import com.rnk0085.android.jetpackcomposepractice.screen.FourthScreen
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
                    uiState = uiState
                )
            }
        ) {
            MyNavHost(navController = nabController)
        }
    }
}

sealed class Screen(val route: String) {
    object First : Screen("first")
    object Second : Screen("second")
    object Third : Screen("third")
    object Fourth : Screen("fourth")
}

@Composable
fun MyBottomNavigation(
    navController: NavController,
    uiState: MainUiState
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        uiState.bottomNavigationItemUiState.forEach { uiState ->
            BottomNavigationItem(
                icon = { Icon(uiState.icon, contentDescription = null) },
                label = { Text(stringResource(uiState.labelId)) },
                selected = currentDestination?.hierarchy?.any { it.route == uiState.screen.route } == true,
                onClick = {
                    navController.navigate(uiState.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun MyNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.First.route
    ) {
        composable(Screen.First.route) { FirstScreen() }
        composable(Screen.Second.route) { SecondScreen() }
        composable(Screen.Third.route) { ThirdScreen() }
        composable(Screen.Fourth.route) { FourthScreen() }
    }
}

@Preview(showBackground = true, name = "MyAppPreview")
@Composable
private fun MyAppPreview() {
    JetpackComposePracticeTheme {
        MyApp()
    }
}
