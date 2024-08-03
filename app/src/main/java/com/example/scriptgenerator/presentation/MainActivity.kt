package com.example.scriptgenerator.presentation

import android.Manifest
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.scriptgenerator.common.generatePdf
import com.example.scriptgenerator.presentation.screens.onboarding.OnboardingScreen
import com.example.scriptgenerator.presentation.theme.ScriptGeneratorTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ){ isGranted ->
        // to do
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScriptGeneratorTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    OnboardingScreen()
                    requestPermissions()
                    generatePdf()
                    Toast.makeText(
                        this@MainActivity,
                        "PDF generated",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }



    private fun requestPermissions(){
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        )
    }
}