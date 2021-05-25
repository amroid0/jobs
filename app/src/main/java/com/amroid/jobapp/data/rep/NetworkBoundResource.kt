package com.amroid.jobapp.data.rep

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.amroid.jobapp.utils.NetworkState
import kotlinx.coroutines.flow.*
import retrofit2.Response

abstract class NetworkBoundResource<RESULT, REQUEST> {

    fun asFlow() = flow<NetworkState<RESULT>> {

        // Emit Loading NetworkState
        emit(NetworkState.loading())

        try {
            // Emit Database content first
            emit(NetworkState.success(fetchFromLocal().first()))

            // Fetch latest posts from remote
            val apiResponse = fetchFromRemote()

            // Parse body
            val remotePosts = apiResponse.body()

            // Check for response validation
            if (apiResponse.isSuccessful && remotePosts != null) {
                // Save posts into the persistence storage
                saveRemoteData(remotePosts)
            } else {
                // Something went wrong! Emit Error NetworkState.
                emit(NetworkState.error(apiResponse.message()))
            }
        } catch (e: Exception) {
            // Exception occurred! Emit error
            emit(NetworkState.error("Network error! Can't get latest posts."))
            e.printStackTrace()
        }

        // Retrieve posts from persistence storage and emit
        emitAll(fetchFromLocal().map {
            NetworkState.success<RESULT>(it)
        })
    }

    /**
     * Saves retrieved from remote into the persistence storage.
     */
    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: REQUEST)

    /**
     * Retrieves all data from persistence storage.
     */
    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>

    /**
     * Fetches [Response] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>
}