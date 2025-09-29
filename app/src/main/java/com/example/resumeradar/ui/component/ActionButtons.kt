package com.example.resumeradar.ui.component


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resumeradar.R
import com.example.resumeradar.ui.CvUiState


@Composable
fun ActionButtons(uiState: CvUiState, onScanClick: () -> Unit, onClearClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Button(
            onClick = onScanClick,
            enabled = uiState.selectedCvUri != null && uiState.jobDescription.isNotBlank(),
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B42F6)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_bolt_24),
                contentDescription = "Scan CV",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Scan CV", color = Color.White, fontSize = 16.sp)
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
            Text("Clear", fontSize = 16.sp)
        }
    }
}
