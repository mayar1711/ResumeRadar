package com.example.resumeradar.ui.component


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumeradar.R
import com.example.resumeradar.ui.CvUiState

@Composable
fun UploadSection(uiState: CvUiState, onPickFile: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onPickFile() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_upload_file_24),
                    contentDescription = "Upload CV",
                    tint = Color(0xFF7B42F6),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    uiState.selectedCvUri?.lastPathSegment ?: "Click to upload your CV",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .border(2.dp, Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
                    .background(Color(0xFFF7F7FA), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_upload_24),
                        contentDescription = "Upload Icon",
                        tint = Color(0xFF7B42F6),
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        uiState.selectedCvUri?.lastPathSegment ?: "Click to upload your CV",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                    Text("Supported formats: PDF, DOCX, TXT", fontSize = 12.sp, color = Color.LightGray)
                }
            }
        }
    }
}
