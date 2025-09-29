package com.example.resumeradar.data.model

data class CvMatchResponse(
    val matchScore: Double,
    val strengths: List<String>,
    val weaknesses: List<String>,
    val improvements: List<String>,
    val summary: String
)
