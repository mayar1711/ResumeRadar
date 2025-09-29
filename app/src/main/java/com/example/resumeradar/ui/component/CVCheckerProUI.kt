package com.example.resumeradar.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.res.painterResource
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.KeyboardArrowUp

import com.example.resumeradar.R
import com.example.resumeradar.ui.CvUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CVCheckerProScreen(

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

        // App Logo/Icon
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
         //       .background(Color(0xFF7B42F6)), // Purple color
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_radar_24),
                contentDescription = "Upload Icon",
                tint = Color(0xFF7B42F6),
                modifier = Modifier.size(48.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        // App Title
        Text(
            text = "CV Reader ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))

        // App Slogan
        Text(
            text = "Analyze your CV with AI technology",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Upload Your CV Section
        Card(
            modifier = Modifier.fillMaxWidth()
            .clickable { onPickFile() },
        shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
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
                        .border(
                            width = 2.dp,
                            color = Color(0xFFE0E0E0), // Light grey border
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            Color(0xFFF7F7FA),
                            shape = RoundedCornerShape(8.dp)
                        ),
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

        Spacer(modifier = Modifier.height(16.dp))

        // Job Description Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_radar_24),
                        contentDescription = "Job Description",
                        tint = Color(0xFF7B42F6),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Job Description",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = uiState.jobDescription,
                    onValueChange = onJobDescriptionChange,
                  //  placeholder = { Text("Paste the job description here for accurate analysis...") },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    placeholder = {
                        Text(
                            text = "Paste the job description here for accurate analysis...",
                            color = Color.LightGray
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
//                    colors = TextFieldDefaults.outlinedTextFieldColors(
//                        focusedBorderColor = Color(0xFF7B42F6),
//                        unfocusedBorderColor = Color(0xFFE0E0E0),
//                        containerColor = Color(0xFFF7F7FA)
//                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Scan CV and Clear Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                    onClick = onScanClick,
                    enabled = uiState.selectedCvUri != null && uiState.jobDescription.isNotBlank(),

                    modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B42F6)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_bolt_24),
                        contentDescription = "Scan CV",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Scan CV", color = Color.White, fontSize = 16.sp)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedButton(
                onClick = onClearClick,

                modifier = Modifier
                    .weight(0.4f)
                    .height(50.dp),
                border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)
            ) {
                Text(text = "Clear", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tips for Best Results Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Tips",
                        tint = Color(0xFF7B42F6),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Tips for Best Results",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                TipItem(text = "Ensure your CV is clear and readable")
                TipItem(text = "Enter the complete job description for accurate analysis")
                TipItem(text = "Use PDF files for the best results")
            }
        }
    }
}

@Composable
fun TipItem(text: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Box(
            modifier = Modifier
                .padding(top = 6.dp)
                .size(6.dp)
                .clip(CircleShape)
                .background(Color(0xFF7B42F6))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 14.sp, color = Color.Gray)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewCVCheckerProScreen() {
//    CVCheckerProScreen()
//}