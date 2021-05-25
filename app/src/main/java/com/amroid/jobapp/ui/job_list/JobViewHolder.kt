package com.amroid.jobapp.ui.job_list


import androidx.recyclerview.widget.RecyclerView
import com.amroid.jobapp.data.model.GithubJob
import com.amroid.jobapp.databinding.ListJobItemRowBinding
import com.amroid.jobapp.utils.OnPositionActionListener


class JobViewHolder(
    private val containerView: ListJobItemRowBinding,
    private val onPositionActionListener: OnPositionActionListener
) : RecyclerView.ViewHolder(containerView.root) {


    fun bind(job: GithubJob) {
        containerView.setJob(job)
        containerView.executePendingBindings()
    itemView.setOnClickListener {
        onPositionActionListener.onPositionAction(JobListContract.Event.OnNavigateToDetail);
    }



    }


}