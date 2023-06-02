package com.lukitateam.lukita.ui.screen.register

data class RegisterState(
    val isLoading : Boolean = false,
    val isSuccess : String? = "",
    val isError : String? = ""
)
