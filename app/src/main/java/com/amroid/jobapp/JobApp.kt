package com.amroid.jobapp

import android.app.Application
import com.amroid.jobapp.di.databaseModule
import com.amroid.jobapp.di.networkModule
import com.amroid.jobapp.di.viewModelModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
class JobApp : Application() {


    override fun onCreate() {
        super.onCreate()

        initKoin()

    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(networkModule, databaseModule, viewModelModule)
        }
    }
}