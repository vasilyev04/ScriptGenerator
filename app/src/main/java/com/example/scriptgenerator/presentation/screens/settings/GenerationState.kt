package com.example.scriptgenerator.presentation.screens.settings

data class GenerationState(
    val thematic: String = "",
    val educationalArea: String = "",
    val participantsCount: String = "",
    val ageCategory: String = "",
    val additionalInformation: String = "",
    val isDataCorrect: Boolean = true
)