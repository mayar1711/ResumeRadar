package com.example.resumeradar.data.model

data class ScanResult(
    val matchScore: Int,
    val strengths: List<String>,
    val weaknesses: List<String>,
    val suggestions: List<String>
)
