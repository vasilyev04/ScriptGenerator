package com.example.scriptgenerator.presentation.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scriptgenerator.domain.entity.GenerationSettings
import com.example.scriptgenerator.domain.usecase.GenerateScriptUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerationSettingsViewModel @Inject constructor(
    private val generateScriptUseCase: GenerateScriptUseCase
): ViewModel(){
    private val _generationState = MutableStateFlow(GenerationState())
    val generationState = _generationState.asStateFlow()

    fun reduce(intent: GenerationSettingsIntent){
        when(intent){
            is GenerationSettingsIntent.OnGenerateScriptButtonClicked -> {
                validateGenerationSettings()
            }
            is GenerationSettingsIntent.ThematicValueChanged -> {
                changeThematic(intent)
                resetDataCorrectness()
            }
            is GenerationSettingsIntent.EducationalAreaValueChanged -> {
                changeEducationalAre(intent)
                resetDataCorrectness()
            }
            is GenerationSettingsIntent.ParticipantsCountValueChanged -> {
                changeParticipantsCount(intent)
                resetDataCorrectness()
            }
            is GenerationSettingsIntent.AgeCategoryValueChanged -> {
                changeAgeCategory(intent)
                resetDataCorrectness()
            }
            is GenerationSettingsIntent.AdditionalInformationValueChanged -> {
                changeAdditionalInformation(intent)
            }

            is GenerationSettingsIntent.OnScriptCleared -> {
                clearScript()
            }
        }
    }

    private fun clearScript(){
        _generationState.update {
            it.copy(
                textScript = ""
            )
        }
    }

    private fun fieldsAreNotEmpty(): Boolean {
        val (
            thematic,
            educationalArea,
            participantsCount,
            ageCategory,
        ) = _generationState.value


        return !(thematic.isBlank() || educationalArea.isBlank()
                || participantsCount.isBlank() || ageCategory.isBlank())
    }

    private fun resetDataCorrectness(){
        _generationState.update {
            it.copy(
                isDataCorrect = true
            )
        }
    }

    private fun validateGenerationSettings(){
        if(!fieldsAreNotEmpty()){
            _generationState.update {
                it.copy(
                    isDataCorrect = false
                )
            }

            return
        }

        val (
            thematic,
            educationalArea,
            participantsCount,
            ageCategory,
            additionalInformation
        ) = _generationState.value

        val settings = GenerationSettings(
            thematic,
            educationalArea,
            participantsCount.toInt(),
            ageCategory,
            additionalInformation
        )

        generateScript(settings)
    }

    private fun generateScript(settings: GenerationSettings){
        _generationState.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            val result = generateScriptUseCase.invoke(settings)
            Log.d("SCRIPT_RESULT", result.text)
            _generationState.update {
                it.copy(
                    textScript = result.text,
                    isLoading = false
                )
            }
        }
    }

    private fun changeThematic(intent: GenerationSettingsIntent.ThematicValueChanged){
        _generationState.value = _generationState.value.copy(thematic = intent.thematic)
    }

    private fun changeEducationalAre(intent: GenerationSettingsIntent.EducationalAreaValueChanged){
        _generationState.value = _generationState.value.copy(educationalArea = intent.area)
    }

    private fun changeParticipantsCount(
        intent: GenerationSettingsIntent.ParticipantsCountValueChanged
    ){
        _generationState.value = _generationState.value.copy(participantsCount = intent.participantsCount)
    }

    private fun changeAgeCategory(intent: GenerationSettingsIntent.AgeCategoryValueChanged){
        _generationState.value = _generationState.value.copy(ageCategory = intent.ageCategory)
    }

    private fun changeAdditionalInformation(
        intent: GenerationSettingsIntent.AdditionalInformationValueChanged
    ){
        _generationState.value = _generationState.value.copy(
            additionalInformation = intent.additionalInformation
        )
    }
}