package com.rnk0085.android.jetpackcomposepractice

import android.util.Log

class CheckTextUseCase {
    operator fun invoke(inputText: String) {
        Log.d("test", "checkText")
        inputText.toIntOrNull()?.let { number ->
            if (number < 0) throw NegativeNumberException()
            if (number > 9) throw OverNumberException()
        } ?: throw NotNumberException()
    }
}
