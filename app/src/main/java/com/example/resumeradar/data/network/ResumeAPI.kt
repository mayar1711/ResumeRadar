package com.example.resumeradar.data.network

import com.example.resumeradar.data.model.CvMatchResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CvApiService {
    @Multipart
    @POST("matchCV")
    suspend fun matchCv(
        @Part file: MultipartBody.Part,
        @Part("jobDescription") jobDescription: RequestBody
    ): CvMatchResponse
}
