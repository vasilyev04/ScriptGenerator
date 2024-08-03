package com.example.scriptgenerator.presentation.screens.onboarding

import androidx.lifecycle.ViewModel
import com.example.scriptgenerator.domain.usecase.GenerateScriptUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val generateScriptUseCase: GenerateScriptUseCase
) : ViewModel()