package com.amroid.jobapp.ui.job_detail

import androidx.lifecycle.viewModelScope
import com.amroid.jobapp.base.BaseViewModel
import com.amroid.jobapp.data.rep.JobRepo
import com.amroid.jobapp.ui.job_list.JobListContract
import com.amroid.jobapp.utils.NetworkState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class JobDetailViewModel (val repo: JobRepo) : BaseViewModel<JobDetailContract.Event, JobDetailContract.State, JobDetailContract.Effect>() {


    /**
     * Create initial State of Views
     */
    override fun createInitialState(): JobDetailContract.State {
        return JobDetailContract.State(
            NetworkState.Idle()
        )
    }


    override fun handleEvent(event: JobDetailContract.Event) {
        when (event) {
            is JobDetailContract.Event.OnFetchJobDetail -> { getJobDetail(event.jobID) }
            is JobDetailContract.Event.OnFavourite -> { favouriteJob(event.jobID) }
            is JobDetailContract.Event.OnUnFavourite -> { unFavouriteJob(event.jobID) }

        }
    }



    private fun getJobDetail(jobID:String) {
        viewModelScope.launch {
            repo.getPostById(postId = "1").collect() {
                setState { copy(jobDetailState = NetworkState.success(it)) }
            }
        }
    }
    private fun favouriteJob(jobID:String) {
        viewModelScope.launch {
            repo.favouriteJob(jobID);
        }
    }

    private fun unFavouriteJob(jobID:String) {
        viewModelScope.launch {
            repo.unFavouriteJob(jobID);
        }
    }
}