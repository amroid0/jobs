package com.amroid.jobapp.di



import com.amroid.jobapp.data.local.JobDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        JobDatabase.getInstance(androidApplication())
    }
    single {
        get<JobDatabase>().jobDao()
    }
}