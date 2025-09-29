package com.example.resumeradar.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
                items = uiState.strengths
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

            // Download button
            Button(
                onClick = { /* TODO: Export PDF/Report */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("⬇ Download Analysis Report", color = Color.White)
            }

            // Next Steps
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

@Composable
fun ScoreCard(score: Double) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Circle with percentage
            Box(
                modifier = Modifier.size(120.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = (score / 100).toFloat(),
                    color = when {
                        score >= 80 -> SuccessGreen
                        score >= 60 -> WarningYellow
                        else -> ErrorRed
                    },
                    strokeWidth = 10.dp,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = "${score.toInt()}%",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(12.dp))
            Text(
                text = when {
                    score >= 80 -> "Excellent"
                    score >= 60 -> "Good"
                    score >= 40 -> "Average"
                    else -> "Poor"
                },
                color = when {
                    score >= 80 -> SuccessGreen
                    score >= 60 -> WarningYellow
                    else -> ErrorRed
                },
                fontWeight = FontWeight.Medium
            )
            Text("Job Requirements Match Score", color = Color.Gray, fontSize = 14.sp)
        }
    }
}

@Composable
fun SectionCard(title: String, color: Color, bgColor: Color, items: List<String>) {
    var expanded by remember { mutableStateOf(true) }

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(Modifier.padding(12.dp)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(title, fontWeight = FontWeight.SemiBold, color = color, fontSize = 18.sp)

                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    tint = color
                )
            }

            Spacer(Modifier.height(8.dp))

            AnimatedVisibility(visible = expanded) {
                Column {
                    items.forEach {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = bgColor)
                        ) {
                            Text(
                                text = it,
                                modifier = Modifier.padding(12.dp),
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

