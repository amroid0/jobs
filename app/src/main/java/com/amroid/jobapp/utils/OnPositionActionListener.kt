package com.amroid.jobapp.utils

import com.amroid.jobapp.ui.job_list.JobListContract

interface OnPositionActionListener {
    fun onPositionAction(positionAction: JobListContract.Event)
}