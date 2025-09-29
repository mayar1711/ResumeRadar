package com.example.resumeradar.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private val SuccessGreen = Color(0xFF16A34A)
private val ErrorRed = Color(0xFFDC2626)
private val WarningYellow = Color(0xFFF59E0B)
@Composable
fun ScoreCard(score: Double) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
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
                    score >= 65 -> "Good"
                    score >= 50 -> "Average"
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



