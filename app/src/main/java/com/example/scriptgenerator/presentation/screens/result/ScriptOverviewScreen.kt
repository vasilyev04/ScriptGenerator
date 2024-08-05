package com.example.scriptgenerator.presentation.screens.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.scriptgenerator.R
import com.example.scriptgenerator.presentation.components.CustomButton
import com.example.scriptgenerator.presentation.components.OutlinedCustomButton
import com.example.scriptgenerator.presentation.theme.inputTextFieldColor
import com.example.scriptgenerator.presentation.theme.labelTextColor
import com.example.scriptgenerator.presentation.theme.textColor

@Composable
fun ScriptOverviewScreen(
    navController: NavController,
    viewModel: ScriptOverviewModel = hiltViewModel()
){
    Column(
        modifier = Modifier.padding(
            start = 15.dp,
            end = 15.dp,
            top = 30.dp,
            bottom = 30.dp
        )
    ) {
        ResultLabel()
        Spacer(modifier = Modifier.padding(top = 36.dp))
        ScriptText(scriptText = "Короче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотретьКороче тут типо готовый текст игры, который можно прокручивать и смотреть")
        Spacer(modifier = Modifier.padding(top = 24.dp))
        GetScriptInPdfButtons()
    }
}

@Composable
fun ResultLabel(){
    Column {
        Text(
            text = stringResource(id = R.string.overview_label),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = labelTextColor
        )
        Spacer(modifier = Modifier.padding(top = 15.dp))
        Text(
            text = stringResource(id = R.string.overview_label_pdf_ready),
            color = textColor
        )
    }
}

@Composable
fun ScriptText(
    scriptText: String
){
    val scroll = rememberScrollState(0)

    Box(
        modifier = Modifier
            .height(400.dp)
            .fillMaxWidth()
            .background(
                color = inputTextFieldColor,
                shape = RoundedCornerShape(10.dp)
            )
            .verticalScroll(scroll)
    ) {
        Text(
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
                top = 16.dp,
                bottom = 16.dp
            ),
            text = scriptText,
            color = textColor,
            fontSize = 14.sp
        )
    }
}

@Composable
fun GetScriptInPdfButtons(){
    CustomButton(
        text = stringResource(id = R.string.overview_way_download),
        onClick = {

        }
    )
    Spacer(modifier = Modifier.padding(top= 10.dp))
    OutlinedCustomButton(
        text = stringResource(id = R.string.overview_way_share),
        onClick = {

        }
    )
    Spacer(modifier = Modifier.padding(top= 17.dp))
    Text(
        modifier = Modifier.fillMaxWidth(),
        fontSize = 16.sp,
        color = textColor,
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.overview_label_choose_way),
    )
    Spacer(modifier = Modifier.padding(top = 15.dp))
    Text(
        modifier = Modifier.fillMaxWidth(),
        fontSize = 16.sp,
        color = Color.Black,
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.overview_generate_again),
    )
}