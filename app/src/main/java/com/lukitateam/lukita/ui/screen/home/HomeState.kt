package com.lukitateam.lukita.ui.screen.home

data class HomeState(
    val isLoading : Boolean = false,
    val isSuccess : String? = "",
    val isError : String? = ""
)