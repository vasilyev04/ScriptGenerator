package com.example.scriptgenerator.presentation.screens.settings

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.scriptgenerator.R
import com.example.scriptgenerator.presentation.components.CustomButton
import com.example.scriptgenerator.presentation.components.DisabledCustomButton
import com.example.scriptgenerator.presentation.components.ExtendedInputTextField
import com.example.scriptgenerator.presentation.components.InputSpinner
import com.example.scriptgenerator.presentation.components.InputTextField
import com.example.scriptgenerator.presentation.components.LoadingCustomButton
import com.example.scriptgenerator.presentation.navigation.Screens
import com.example.scriptgenerator.presentation.theme.backgroundColor
import com.example.scriptgenerator.presentation.theme.labelTextColor
import com.example.scriptgenerator.presentation.theme.textColor

@Composable
fun GenerationSettingsScreen(
    navController: NavController,
    viewModel: GenerationSettingsViewModel = hiltViewModel()
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 15.dp,
                end = 15.dp,
                top = 30.dp
            )
    ) {
        val state by viewModel.generationState.collectAsState()
        val options = stringArrayResource(id = R.array.settings_educational_area_options).toList()
        LaunchedEffect(Unit) {
            viewModel.reduce(GenerationSettingsIntent.EducationalAreaValueChanged(options[0]))
        }

        if(!state.isDataCorrect){
            Toast.makeText(
                LocalContext.current,
                stringResource(id = R.string.error_empty_fields),
                Toast.LENGTH_SHORT
            ).show()
        }

        if(state.textScript.isNotBlank()){
            navController.navigate(
                Screens.ScriptOverview.route.replace(
                    oldValue = "{script}",
                    newValue = state.textScript
                )
            )
            viewModel.reduce(GenerationSettingsIntent.OnScriptCleared)
        }

        SettingsLabel()

        Spacer(modifier = Modifier.padding(top = 43.dp))
        ThematicInput(
            state.thematic,
            onTextChanged = {
                viewModel.reduce(GenerationSettingsIntent.ThematicValueChanged(it))
            }
        )

        Spacer(modifier = Modifier.padding(top = 25.dp))
        AreaInput(
            defaultSelected = state.educationalArea,
            options = options,
            onSelectChanged = {
                viewModel.reduce(GenerationSettingsIntent.EducationalAreaValueChanged(it))
            }
        )

        Spacer(modifier = Modifier.padding(top = 25.dp))
        ParticipantsCountInput(
            participantsCount = state.participantsCount,
            onTextChanged = {
                viewModel.reduce(GenerationSettingsIntent.ParticipantsCountValueChanged(it))
            }
        )

        Spacer(modifier = Modifier.padding(top = 25.dp))
        AgeCategoryInput(
            ageCategory = state.ageCategory,
            onTextChanged = {
                viewModel.reduce(GenerationSettingsIntent.AgeCategoryValueChanged(it))
            }
        )

        Spacer(modifier = Modifier.padding(top = 25.dp))
        AdditionalInformationInput(
            additionalInformation = state.additionalInformation,
            onTextChanged = {
                viewModel.reduce(GenerationSettingsIntent.AdditionalInformationValueChanged(it))
            }
        )

        Spacer(modifier = Modifier.padding(top = 71.dp))
        if(state.isLoading){
            LoadingCustomButton()
        }else if(!state.isDataCorrect){
            DisabledCustomButton(stringResource(id = R.string.settings_generate),)
        }else{
            CustomButton(
                stringResource(id = R.string.settings_generate),
                onClick = {
                    viewModel.reduce(GenerationSettingsIntent.OnGenerateScriptButtonClicked)
                }
            )
        }
    }

}

@Composable
fun SettingsLabel(){
    Column {
        Text(
            text = stringResource(id = R.string.settings_label),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = labelTextColor
        )
        Spacer(modifier = Modifier.padding(top = 15.dp))
        Text(
            text = stringResource(id = R.string.settings_label_choose_game),
            color = textColor
        )
    }
}

@Composable
fun ThematicInput(
    thematic: String,
    onTextChanged: (String) -> Unit
){
    Column {
        Text(
            text = stringResource(id = R.string.settings_label_thematic),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(top = 3.dp))
        InputTextField(
            value = thematic,
            hint = stringResource(R.string.hint_thematic),
            onTextChanged = {
                onTextChanged(it)
            }
        )
    }
}

@Composable
fun AreaInput(
    defaultSelected: String,
    options: List<String>,
    onSelectChanged: (String) -> Unit
){
    Column {
        Text(
            text = stringResource(id = R.string.settings_label_area),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(top = 3.dp))
        InputSpinner(
            list = options,
            defaultSelected = defaultSelected,
            color = textColor,
            onSelected = {
                onSelectChanged(options[it])
            }
        )
    }
}

@Composable
fun ParticipantsCountInput(
    participantsCount: String,
    onTextChanged: (String) -> Unit
){
    Column {
        Text(
            text = stringResource(id = R.string.settings_label_participants),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(top = 3.dp))
        InputTextField(
            value = participantsCount,
            hint = "20",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onTextChanged = {
                onTextChanged(it)
            }
        )
    }
}

@Composable
fun AgeCategoryInput(
    ageCategory: String,
    onTextChanged: (String) -> Unit
){
    Column {
        Text(
            text = stringResource(id = R.string.settings_label_age_category),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(top = 3.dp))
        InputTextField(
            value = ageCategory,
            hint = "4-6",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onTextChanged = {
                onTextChanged(it)
            }
        )
    }
}

@Composable
fun AdditionalInformationInput(
    additionalInformation: String,
    onTextChanged: (String) -> Unit
){
    Column {
        Text(
            text = stringResource(id = R.string.settings_label_details),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(top = 3.dp))
        ExtendedInputTextField(
            modifier = Modifier.height(85.dp),
            value = additionalInformation,
            hint = stringResource(R.string.hint_additional_info),
            onTextChanged = {
                onTextChanged(it)
            }
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Text(
            text = stringResource(id = R.string.settings_hint_details),
            color = textColor,
            fontSize = 12.sp
        )
    }
}