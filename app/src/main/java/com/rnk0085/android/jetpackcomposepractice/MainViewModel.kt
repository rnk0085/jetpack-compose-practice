package com.rnk0085.android.jetpackcomposepractice

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    // 本当はDIすべき
    val checkTextUseCase = CheckTextUseCase()

    private val _errorState = MutableStateFlow(ErrorState())
    val errorState: StateFlow<ErrorState> = _errorState.asStateFlow()

    private fun checkText(inputText: String) {
        checkTextUseCase(inputText)
    }

    fun isValidNumber(inputText: String) {
        Log.d("test", "isValidNumber")
        try {
            checkText(inputText)
            Log.d("test", "isValidNumber: checkTextDone")
        } catch (e: MyException) {
            Log.d("test", "isValidNumber: catch Exception")
            _errorState.update {
                it.copy(
                    errorMessage = when (e) {
                        is NotNumberException -> "数字を入力してください"
                        is NegativeNumberException -> "正の数を入力してください"
                        is OverNumberException -> "9までの数字を入力してください"
                    },
                    isError = true,
                    isShown = false
                )
            }
        }
    }

    fun onDismissRequest() {
        _errorState.update {
            it.copy(isShown = true)
        }
    }
}

data class ErrorState(
    // TODO: 冗長な気がする
    val errorMessage: String = "",
    val isError: Boolean = false,
    val isShown: Boolean = false
)
