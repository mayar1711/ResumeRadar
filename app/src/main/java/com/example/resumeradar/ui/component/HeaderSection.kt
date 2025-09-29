package com.example.resumeradar.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumeradar.R


@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.outline_radar_24),
            contentDescription = "Logo",
            tint = Color(0xFF7B42F6),
            modifier = Modifier.size(48.dp)
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text("CV Reader", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(4.dp))
    Text("Analyze your CV with AI technology", fontSize = 14.sp, color = Color.Gray)
}