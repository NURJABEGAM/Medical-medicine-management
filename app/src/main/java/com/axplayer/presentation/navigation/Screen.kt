package com.axplayer.presentation.navigation

sealed class Screen(val route: String) {
    data object Player : Screen("player/{videoId}") {
        fun createRoute(videoId: String) = "player/$videoId"
    }
    data object Library : Screen("library")
    data object Settings : Screen("settings")
}
