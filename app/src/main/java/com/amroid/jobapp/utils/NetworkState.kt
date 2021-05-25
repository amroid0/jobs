package com.amroid.jobapp.utils

sealed class NetworkState<T> {
    class Loading<T> : NetworkState<T>()
    class Idle<T> : NetworkState<T>()

    data class Success<T>(val data: T) : NetworkState<T>()

    data class Error<T>(val message: String) : NetworkState<T>()

    companion object {

        fun <T> idle() = Idle<T>()
        /**
         * Returns [State.Loading] instance.
         */
        fun <T> loading() = Loading<T>()

        /**
         * Returns [State.Success] instance.
         * @param data Data to emit with status.
         */
        fun <T> success(data: T) =
            Success(data)

        /**
         * Returns [State.Error] instance.
         * @param message Description of failure.
         */
        fun <T> error(message: String) =
            Error<T>(message)
    }

}