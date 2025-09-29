package com.example.resumeradar.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumeradar.ui.CvUiState

private val PrimaryBlue = Color(0xFF7B42F6)
private val SuccessGreen = Color(0xFF16A34A)
private val ErrorRed = Color(0xFFDC2626)
private val WarningYellow = Color(0xFFF59E0B)
private val LightGray = Color(0xFFF7F7FA)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(uiState: CvUiState, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Analysis Result") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.Close, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->

        val isLoading = uiState.matchScore == null &&
                uiState.strengths.isEmpty() &&
                uiState.weaknesses.isEmpty() &&
                uiState.suggestions.isEmpty()

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = PrimaryBlue)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                uiState.matchScore?.let { ScoreCard(score = it) }

                SectionCard(
                    title = "Strengths",
                    color = SuccessGreen,
                    bgColor = Color(0xFFD1FAE5),
                    items = uiState.strengths,
                 //   initiallyExpanded = true
                    )

                SectionCard(
                    title = "Areas for Improvement",
                    color = ErrorRed,
                    bgColor = Color(0xFFFECACA),
                    items = uiState.weaknesses
                )

                SectionCard(
                    title = "Recommendations",
                    color = PrimaryBlue,
                    bgColor = Color(0xFFEDE9FE),
                    items = uiState.suggestions
                )

                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = LightGray)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(
                            "Next Steps for Improvement",
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryBlue
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Use the provided recommendations to enhance your CV and increase your chances of success",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}
