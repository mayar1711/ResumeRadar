package com.example.resumeradar

import android.app.Application
import com.example.resumeradar.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ResumeRadarApp : Application (){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ResumeRadarApp)
            modules(
               listOf(
                   appModule
               )
            )

        }
    }
}