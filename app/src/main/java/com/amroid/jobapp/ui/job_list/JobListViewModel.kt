package com.amroid.jobapp.ui.job_list

import androidx.lifecycle.viewModelScope
import com.amroid.jobapp.base.BaseViewModel
import com.amroid.jobapp.data.rep.JobRepo
import com.amroid.jobapp.utils.NetworkState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class JobListViewModel(val repo:JobRepo) : BaseViewModel<JobListContract.Event, JobListContract.State, JobListContract.Effect>() {


    /**
     * Create initial State of Views
     */
    override fun createInitialState(): JobListContract.State {
        return JobListContract.State(
            NetworkState.Idle()
        )
    }

    /**
     * Handle each event
     */
    override fun handleEvent(event: JobListContract.Event) {
        when (event) {
            is JobListContract.Event.OnFetchJob -> { getJobList() }
            is JobListContract.Event.OnNavigateToDetail -> {
                setEffect { JobListContract.Effect.NavigateToDetail }
            }
        }
    }


    /**
     * Generate a random number
     */
    private fun getJobList() {
        viewModelScope.launch {
            repo.getAllPosts().collect {
                setState { copy(jobListState = it) }
            }

            // Set Loading
        }
    }
}