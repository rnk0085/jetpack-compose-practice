package com.rnk0085.android.jetpackcomposepractice

sealed class MyException : Exception()

/**
 * 条件「0~9の数字を入力」
 * ・数字以外が入力された
 * ・負の数
 * ・2桁以上
 */
class NotNumberException : MyException()

class NegativeNumberException : MyException()

class OverNumberException : MyException()
