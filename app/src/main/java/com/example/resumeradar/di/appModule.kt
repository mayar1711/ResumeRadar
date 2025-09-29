package com.example.resumeradar.di

import com.example.resumeradar.data.network.CvApiService
import com.example.resumeradar.data.repo.CvRepositoryImpl
import com.example.resumeradar.data.repo.ICvRepository
import com.example.resumeradar.ui.CvViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("http://10.145.19.184:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CvApiService::class.java)
    }

    // Bind interface to implementation
    single<ICvRepository> { CvRepositoryImpl(get()) }

    viewModel { CvViewModel(get()) }
}
