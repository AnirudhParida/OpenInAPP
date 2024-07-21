package com.example.openinapp.util

sealed class  Screens(val route: String) {
    data object Links : Screens("links")
    data object Profile : Screens("profile")
    data object Campaign : Screens("campaign")
    data object Courses : Screens("courses")
}