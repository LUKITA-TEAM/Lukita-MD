package com.lukitateam.lukita.util

fun validateEmail(text: String): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    return emailRegex.matches(text)
}

fun validatePassword(text : String) : Boolean {
    return text.length >= 8
}
fun comparingPassword(old : String, text : String) : Boolean {
    return old == text
}