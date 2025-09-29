package com.example.resumeradar.data.repo

import com.example.resumeradar.data.model.CvMatchResponse
import com.example.resumeradar.data.network.CvApiService
import okhttp3.MultipartBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody


class CvRepositoryImpl(private val api: CvApiService) : ICvRepository {

    override suspend fun matchCv(
        file: MultipartBody.Part,
        jobDescription: String
    ): CvMatchResponse {
        val jobDescRequest = RequestBody.create("text/plain".toMediaTypeOrNull(), jobDescription)
        return api.matchCv(file, jobDescRequest)
    }
}
