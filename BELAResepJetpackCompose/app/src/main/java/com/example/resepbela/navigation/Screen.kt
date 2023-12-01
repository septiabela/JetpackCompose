package com.example.resepbela.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailResep : Screen("home/{resepId}") {
        fun createRoute(resepId: Int) = "home/$resepId"
    }
}