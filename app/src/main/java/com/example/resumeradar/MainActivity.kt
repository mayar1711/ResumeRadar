package com.example.resumeradar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resumeradar.ui.CvViewModel
import com.example.resumeradar.ui.component.ResultScreen
import com.example.resumeradar.ui.component.CVCheckerScreen
import com.example.resumeradar.utility.uriToFile
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

                MaterialTheme {
                    val navController = rememberNavController()
                    val vm: CvViewModel = koinViewModel()
                        val uiState by vm.uiState.collectAsState()

                    NavHost(
                        navController = navController,
                        startDestination = "upload"
                    ) {
                        composable("upload") {
                            val pickFileLauncher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.GetContent()
                            ) { uri ->
                                if (uri != null) {
                                    vm.updateSelectedCv(uri)
                                }
                            }

                            CVCheckerScreen(
                                uiState = uiState,
                                onPickFile = { pickFileLauncher.launch("application/pdf") },
                                onJobDescriptionChange = { vm.updateJobDescription(it) },
                                onScanClick = {
                                    val uri = uiState.selectedCvUri
                                    if (uri != null) {
                                        val file = uriToFile(uri, this@MainActivity)
                                        vm.uploadCvAndJobDescription(file, uiState.jobDescription)
                                        navController.navigate("result")
                                    }
                                },
                                onClearClick = { vm.clear() }
                            )

                        }
                        composable("result") {
                            ResultScreen(
                                uiState = uiState,
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }