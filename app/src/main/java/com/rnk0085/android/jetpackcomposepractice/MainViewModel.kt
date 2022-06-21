package com.rnk0085.android.jetpackcomposepractice

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<MainUiState>

    init {
        val bottomBarUiState = listOf(
            BottomNavigationItemUiState(
                bottomTab = BottomTab.Home,
                icon = Icons.Filled.Home,
                labelId = R.string.home
            ),
            BottomNavigationItemUiState(
                bottomTab = BottomTab.Email,
                icon = Icons.Filled.Email,
                labelId = R.string.email
            ),
            BottomNavigationItemUiState(
                bottomTab = BottomTab.Star,
                icon = Icons.Filled.Star,
                labelId = R.string.stars
            ),
            BottomNavigationItemUiState(
                bottomTab = BottomTab.Lists,
                icon = Icons.Filled.List,
                labelId = R.string.lists
            ),
        )

        _uiState = MutableStateFlow(MainUiState(bottomBarUiState))
    }

    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun changeSelectedTab(selectedTab: String) {
        _uiState.update {
            it.copy(selectedTab = selectedTab)
        }
    }
}

data class MainUiState(
    val bottomNavigationItemUiState: List<BottomNavigationItemUiState>,
    val selectedTab: String = BottomTab.Home.route
)

data class BottomNavigationItemUiState(
    val bottomTab: BottomTab,
    val icon: ImageVector,
    @StringRes val labelId: Int
)
