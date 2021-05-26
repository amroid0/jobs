package com.amroid.jobapp.ui.job_detail



import com.amroid.jobapp.base.UiEffect
import com.amroid.jobapp.base.UiEvent
import com.amroid.jobapp.base.UiState
import com.amroid.jobapp.data.model.GithubJob
import com.amroid.jobapp.utils.NetworkState


class JobDetailContract {

    sealed class Event : UiEvent {
        data class OnFetchJobDetail(val jobID:String) : Event()
        data class OnFavourite(val jobID:String) : Event()
        data class   OnUnFavourite(val jobID:String) :Event()
        }
    data class State(
        val jobDetailState: NetworkState<GithubJob>
    ) : UiState


    sealed class Effect : UiEffect {

    }

}