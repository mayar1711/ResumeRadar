package com.example.resumeradar.data.repo

import com.example.resumeradar.data.model.CvMatchResponse
import okhttp3.MultipartBody

interface ICvRepository  {
    suspend fun matchCv(file: MultipartBody.Part, jobDescription: String): CvMatchResponse
}