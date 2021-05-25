package com.amroid.jobapp.data.rep

import androidx.annotation.MainThread
import com.amroid.jobapp.data.local.JobDao
import com.amroid.jobapp.utils.NetworkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import  com.amroid.jobapp.data.remote.GithubApi
import  com.amroid.jobapp.data.model.GithubJob
import kotlinx.coroutines.flow.flowOn


class JobRepo constructor(
    private val jobDao: JobDao,
    private val jobService: GithubApi
) {

    /**
     * Fetched the posts from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    fun getAllPosts(): Flow<NetworkState<List<GithubJob>>> {
        return object : NetworkBoundResource<List<GithubJob>, List<GithubJob>>() {

            override suspend fun saveRemoteData(response: List<GithubJob>) =
                jobDao.insertjobs(response)

            override fun fetchFromLocal(): Flow<List<GithubJob>> =jobDao.getAlljobs()

            override suspend fun fetchFromRemote(): Response<List<GithubJob>> =jobService.fetchJobs()

        }.asFlow().flowOn(Dispatchers.IO)
    }


    @MainThread
    fun getPostById(postId: String): Flow<GithubJob> = jobDao.getJobById(postId)
}