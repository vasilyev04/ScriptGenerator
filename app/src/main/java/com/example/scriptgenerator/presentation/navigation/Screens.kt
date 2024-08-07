package com.example.scriptgenerator.presentation.navigation

sealed class Screens(
    val route: String
) {
    data object GenerationSettings: Screens(route = "generation_settings")
    data object ScriptOverview: Screens(route = "script_overview/{script}")
}