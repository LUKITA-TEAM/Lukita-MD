package com.lukitateam.lukita.ui.screen.login

data class LoginState(
    val isLoading : Boolean = false,
    val isSuccess : String? = "",
    val isError : String? = ""
)
