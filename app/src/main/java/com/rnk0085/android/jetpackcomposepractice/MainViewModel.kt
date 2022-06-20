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

class MainViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<MainUiState>

    init {
        val bottomBarUiState = listOf(
            BottomNavigationItemUiState(
                screen = Screen.First,
                icon = Icons.Filled.Home,
                labelId = R.string.home
            ),
            BottomNavigationItemUiState(
                screen = Screen.Second,
                icon = Icons.Filled.Email,
                labelId = R.string.email
            ),
            BottomNavigationItemUiState(
                screen = Screen.Third,
                icon = Icons.Filled.Star,
                labelId = R.string.stars
            ),
            BottomNavigationItemUiState(
                screen = Screen.Fourth,
                icon = Icons.Filled.List,
                labelId = R.string.lists
            ),
        )

        _uiState = MutableStateFlow(MainUiState(bottomBarUiState))
    }

    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()
}

data class MainUiState(
    val bottomNavigationItemUiState: List<BottomNavigationItemUiState>,
)

data class BottomNavigationItemUiState(
    val screen: Screen,
    val icon: ImageVector,
    @StringRes val labelId: Int
)
