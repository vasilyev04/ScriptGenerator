package com.example.scriptgenerator.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.compose.rememberNavController
import com.example.scriptgenerator.presentation.screens.App
import com.example.scriptgenerator.presentation.theme.ScriptGeneratorTheme
import dagger.hilt.android.AndroidEntryPoint


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
            val navController = rememberNavController()
            ScriptGeneratorTheme {
                App(navController)
//                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
////                    requestPermissions()
////                    generatePdf()
////                    Toast.makeText(
////                        this@MainActivity,
////                        "PDF generated",
////                        Toast.LENGTH_SHORT
////                    ).show()
//                }
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