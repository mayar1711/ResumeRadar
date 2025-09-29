package com.example.resumeradar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
private val PrimaryBlue = Color(0xFF2563EB)
private val SuccessGreen = Color(0xFF16A34A)
private val ErrorRed = Color(0xFFDC2626)
private val LightGray = Color(0xFFF3F4F6)

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                uiState.isLoading -> {  }

                uiState.matchScore != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ScoreCard(score = uiState.matchScore)

                        SectionCard("Strengths", SuccessGreen, uiState.strengths)
                        SectionCard("Weaknesses", ErrorRed, uiState.weaknesses)
                        SectionCard("Suggestions", PrimaryBlue, uiState.suggestions)
                    }
                }

                uiState.errorMessage != null -> {
                    Text(
                        "Error: ${uiState.errorMessage}",
                        color = ErrorRed,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

        }
    }
}

@Composable
fun ScoreCard(score: Double) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(80.dp), contentAlignment = Alignment.Center) {
                Text(
                    text = "$score%",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = when {
                        score >= 80 -> SuccessGreen
                        score >= 60 -> Color(0xFFF59E0B)
                        else -> ErrorRed
                    }
                )
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text("Match Score", fontWeight = FontWeight.SemiBold)
                Text(
                    when {
                        score >= 80 -> "Excellent fit"
                        score >= 60 -> "Good fit"
                        score >= 40 -> "Average fit"
                        else -> "Poor fit"
                    },
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun SectionCard(title: String, color: Color, items: List<String>) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(12.dp)) {
            Text(title, fontWeight = FontWeight.SemiBold, color = color)
            Spacer(Modifier.height(8.dp))
            items.forEach {
                Text("• $it", fontSize = 14.sp)
            }
        }
    }
}
