package com.amroid.jobapp.ui.job_list

import com.amroid.jobapp.base.UiEffect
import com.amroid.jobapp.base.UiEvent
import com.amroid.jobapp.base.UiState
import com.amroid.jobapp.data.model.GithubJob
import com.amroid.jobapp.utils.NetworkState


class JobListContract {

    sealed class Event : UiEvent {
        object OnFetchJob : Event()
        object OnNavigateToDetail : Event()

    }

    data class State(
        val jobListState: NetworkState<List<GithubJob>>
    ) : UiState



    sealed class Effect : UiEffect {

        object NavigateToDetail : Effect()

    }

}