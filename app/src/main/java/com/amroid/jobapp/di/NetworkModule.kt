package com.amroid.jobapp.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.amroid.jobapp.data.remote.GithubApi
import  com.amroid.jobapp.data.rep.JobRepo

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(GithubApi.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(GithubApi::class.java)
    }
    single {
        JobRepo(get(), get())
    }
}