package com.example.scriptgenerator.presentation.screens.onboarding


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.scriptgenerator.BuildConfig

@Composable
fun OnboardingScreen(viewModel: OnboardingViewModel = hiltViewModel()){
    Greeting(name = BuildConfig.GEMINI_API_KEY )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
