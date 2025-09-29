package com.example.resumeradar.ui

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resumeradar.data.repo.ICvRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class CvViewModel(private val repository: ICvRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(CvUiState())
    val uiState: StateFlow<CvUiState> = _uiState

    fun uploadCvAndJobDescription(file: File, jobDescription: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)

                val requestFile = file.asRequestBody("application/pdf".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

                val response = repository.matchCv(body, jobDescription)

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    matchScore = response.matchScore,
                    strengths = response.strengths,
                    weaknesses = response.weaknesses,
                    suggestions = response.improvements,
                )
            } catch (e: Exception) {
                Log.e("CvViewModel", "Error uploading CV", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.localizedMessage

                )
            }
        }
    }


    fun updateJobDescription(desc: String) {
        _uiState.value = _uiState.value.copy(jobDescription = desc)
    }

    fun clear() {
        _uiState.value = CvUiState()
    }


    fun updateSelectedCv(uri: Uri) {
        _uiState.value = _uiState.value.copy(selectedCvUri = uri)
    }

}
