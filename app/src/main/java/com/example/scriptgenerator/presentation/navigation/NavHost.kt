package com.example.scriptgenerator.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.scriptgenerator.presentation.screens.result.ScriptOverviewScreen
import com.example.scriptgenerator.presentation.screens.settings.GenerationSettingsScreen

@Composable
fun NavHost(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.GenerationSettings.route
    ) {
        composable(route = Screens.GenerationSettings.route) {
            GenerationSettingsScreen(navController = navController)
        }

        composable(route = Screens.ScriptOverview.route) {
            val script = it.arguments?.getString("script") ?: ""
            ScriptOverviewScreen(
                navController = navController,
                textScript = script
            )
        }
    }
}