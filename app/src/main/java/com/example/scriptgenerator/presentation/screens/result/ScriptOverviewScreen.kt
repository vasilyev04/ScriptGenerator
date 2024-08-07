package com.example.scriptgenerator.presentation.screens.result

import android.Manifest
import android.content.Intent
import android.content.Intent.CATEGORY_DEFAULT
import android.content.Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.net.Uri
import android.os.Build
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.scriptgenerator.R
import com.example.scriptgenerator.common.generatePdfInDownloadPath
import com.example.scriptgenerator.presentation.components.CustomButton
import com.example.scriptgenerator.presentation.components.OutlinedCustomButton
import com.example.scriptgenerator.presentation.navigation.Screens
import com.example.scriptgenerator.presentation.theme.backgroundColor
import com.example.scriptgenerator.presentation.theme.inputTextFieldColor
import com.example.scriptgenerator.presentation.theme.labelTextColor
import com.example.scriptgenerator.presentation.theme.textColor
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import java.io.File

@Composable
fun ScriptOverviewScreen(
    navController: NavController,
    textScript: String
){
    val shouldShareFile = remember {
        mutableStateOf(false)
    }

    val shouldDownloadFile = remember {
        mutableStateOf(false)
    }

    if(shouldShareFile.value){
        sharePdfFile(generatePdfInDownloadPath(textScript))
        shouldShareFile.value = false
    }

    if(shouldDownloadFile.value){
        generatePdfInDownloadPath(textScript)
        shouldDownloadFile.value = false
        Toast.makeText(
            LocalContext.current,
            stringResource(id = R.string.file_downloaded),
            Toast.LENGTH_SHORT
        ).show()
    }

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
        ScriptText(scriptText = textScript)
        Spacer(modifier = Modifier.padding(top = 24.dp))
        GetScriptInPdfButtons(
            textScript,
            downloadButtonClicked = {
                shouldDownloadFile.value = true
            },
            shareButtonClicked = {
                shouldShareFile.value = true
            },
            generateAgainButtonClicked = {
                navController.navigate(Screens.GenerationSettings.route)
            }
        )
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
fun GetScriptInPdfButtons(
    textScript: String,
    downloadButtonClicked: () -> Unit,
    shareButtonClicked: () -> Unit,
    generateAgainButtonClicked: () -> Unit
){
    CustomButton(
        text = stringResource(id = R.string.overview_way_download),
        onClick = {
            downloadButtonClicked()
        }
    )
    Spacer(modifier = Modifier.padding(top= 10.dp))
    OutlinedCustomButton(
        text = stringResource(id = R.string.overview_way_share),
        onClick = {
            shareButtonClicked()
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
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                generateAgainButtonClicked()
            },
        fontSize = 16.sp,
        color = Color.Black,
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.overview_generate_again),
    )
}

@Composable
private fun sharePdfFile(filePath: String) {
    val context = LocalContext.current
    val file = File(filePath)
    val fileUri: Uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        file
    )

    val shareIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, fileUri)
        type = "application/pdf"
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share PDF via"))
}
