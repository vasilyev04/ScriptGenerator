package com.example.scriptgenerator.domain.entity

data class GenerationSettings(
    val thematic: String,
    val educationalArea: String,
    val participantsCount: Int,
    val ageCategory: String,
    val additionalInformation: String,
)