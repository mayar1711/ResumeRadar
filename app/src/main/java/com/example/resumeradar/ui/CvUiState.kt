package com.example.resumeradar.ui


import android.net.Uri
import com.example.resumeradar.data.model.CvMatchResponse

data class CvUiState(
    val selectedCvUri: Uri? = null,
    val jobDescription: String = "",
    val matchScore: Double? = null,
    val strengths: List<String> = emptyList(),
    val weaknesses: List<String> = emptyList(),
    val suggestions: List<String> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
