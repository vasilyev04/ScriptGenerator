package com.example.scriptgenerator.presentation.screens.settings


interface GenerationSettingsIntent {
    object OnGenerateScriptButtonClicked: GenerationSettingsIntent

    data class ThematicValueChanged(
        val thematic: String
    ): GenerationSettingsIntent

    data class EducationalAreaValueChanged(
        val area: String
    ): GenerationSettingsIntent

    data class ParticipantsCountValueChanged(
        val participantsCount: String
    ): GenerationSettingsIntent

    data class AgeCategoryValueChanged(
        val ageCategory: String
    ): GenerationSettingsIntent

    data class AdditionalInformationValueChanged(
        val additionalInformation: String
    ): GenerationSettingsIntent

}