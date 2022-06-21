package com.rnk0085.android.jetpackcomposepractice

/**
 * Home: top, next
 * Email: top
 * Star: top
 * Lists: top, detail
 */
class Screen {

}

// 「Screen.values().map { it.route }」を使うために必要
enum class BottomTab(val route: String) {
    Home("home"),
    Email("email"),
    Star("star"),
    Lists("lists")
}

// Homeタブ内のナビゲーションに使う
sealed class HomeRoutes(val route: String) {
    // BottomTabを持つものは必ず1つは必要
    object Home : HomeRoutes("${BottomTab.Home.route}/top")
    object Next : HomeRoutes("next")
    // object xx: HomeRoutes("xx") のように増える可能性あり
}

sealed class ListRoutes(val route: String) {
    object Lists : ListRoutes("${BottomTab.Lists.route}/top")
    object Detail : ListRoutes("detail")
}
