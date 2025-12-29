package com.axplayer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.axplayer.presentation.library.LibraryScreen
import com.axplayer.presentation.player.PlayerScreen
import com.axplayer.presentation.settings.SettingsScreen

@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: String = Screen.Library.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Library.route) {
            LibraryScreen(
                onVideoClick = { videoId ->
                    navController.navigate(Screen.Player.createRoute(videoId))
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        composable(
            route = Screen.Player.route,
            arguments = listOf(
                navArgument("videoId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val videoId = backStackEntry.arguments?.getString("videoId") ?: return@composable
            PlayerScreen(
                videoId = videoId,
                onBackPress = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onBackPress = {
                    navController.popBackStack()
                }
            )
        }
    }
}
