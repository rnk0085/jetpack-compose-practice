package com.rnk0085.android.jetpackcomposepractice

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var errorMessage = ""

    private fun checkText(inputText: String) {
        Log.d("test", "checkText")
        inputText.toIntOrNull()?.let { number ->
            if (number < 0) throw NegativeNumberException()
            if (number > 9) throw OverNumberException()
        } ?: throw NotNumberException()
    }

    fun isValidNumber(inputText: String): Boolean {
        Log.d("test", "isValidNumber")
        return try {
            checkText(inputText)
            Log.d("test", "isValidNumber: checkTextDone")
            true
        } catch (e: MyException) {
            Log.d("test", "isValidNumber: catch Exception")
            errorMessage = when (e) {
                is NotNumberException -> "数字を入力してください"
                is NegativeNumberException -> "正の数を入力してください"
                is OverNumberException -> "9までの数字を入力してください"
            }
            Log.d("test", "errorMessage: $errorMessage")
            false
        }
    }
}
