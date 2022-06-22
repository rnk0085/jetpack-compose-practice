package com.rnk0085.android.jetpackcomposepractice

import com.rnk0085.android.jetpackcomposepractice.Home.Companion.tab

/**
 * Home: top, next
 * Email: top
 * Star: top
 * Lists: top, detail
 */
// 「Screen.values().map { it.route }」を使うために必要
enum class BottomTab(val route: String) {
    Home("home"),
    Email("email"),
    Star("star"),
    Lists("lists")
}

sealed interface Screen {
    val route: String
}

sealed interface Tab {
    val tab: BottomTab
}

sealed interface Home : Screen {
    object Top : Home {
        override val route: String = "${tab.route}/top"
    }

    object Next : Home {
        override val route: String = "${tab.route}/next"
    }

    companion object : Tab {
        override val tab: BottomTab = BottomTab.Home
    }
}

sealed interface Lists : Screen {
    object Top : Lists {
        override val route: String = "${tab.route}/top"
    }

    object Detail : Lists {
        override val route: String = "${tab.route}/detail"
    }

    companion object : Tab {
        override val tab: BottomTab = BottomTab.Lists
        const val MEMO_ID_KEY = "memoId"
    }
}

sealed class ListRoutes(val route: String) {
    object Lists : ListRoutes("${BottomTab.Lists.route}/top")
    object Detail : ListRoutes("detail")
}
