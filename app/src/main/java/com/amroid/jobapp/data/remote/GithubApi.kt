package com.amroid.jobapp.data.remote

import com.amroid.jobapp.data.model.GithubJob
import retrofit2.Response
import retrofit2.http.GET


interface GithubApi {

    companion object {
        const val BASE_URL = "https://jobs.github.com/"
    }

    @GET("positions.json")
    suspend   fun fetchJobs(): Response<List<GithubJob>>
}