package com.example.resumeradar.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.resumeradar.ui.CvUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CVCheckerScreen(
    uiState: CvUiState,
    onPickFile: () -> Unit,
    onJobDescriptionChange: (String) -> Unit,
    onScanClick: () -> Unit,
    onClearClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7FA))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        HeaderSection()

        Spacer(modifier = Modifier.height(32.dp))

        UploadSection(uiState, onPickFile)

        Spacer(modifier = Modifier.height(16.dp))

        JobDescriptionSection(uiState, onJobDescriptionChange)

        Spacer(modifier = Modifier.height(24.dp))

        ActionButtons(uiState, onScanClick, onClearClick)

        Spacer(modifier = Modifier.height(24.dp))

        TipsSection()
    }
}