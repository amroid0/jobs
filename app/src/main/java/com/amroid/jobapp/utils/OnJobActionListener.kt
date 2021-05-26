package com.amroid.jobapp.utils

import com.amroid.jobapp.data.model.GithubJob
import com.amroid.jobapp.ui.job_list.JobListContract

interface OnJobActionListener {
    fun onJobClicked(positionAction: JobListContract.Event, job:GithubJob)

}