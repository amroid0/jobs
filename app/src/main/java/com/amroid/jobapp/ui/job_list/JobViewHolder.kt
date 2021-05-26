package com.amroid.jobapp.ui.job_list


import androidx.recyclerview.widget.RecyclerView
import com.amroid.jobapp.data.model.GithubJob
import com.amroid.jobapp.databinding.ListJobItemRowBinding
import com.amroid.jobapp.utils.OnJobActionListener


class JobViewHolder(
    private val binding: ListJobItemRowBinding,
    private val onJobActionListener: OnJobActionListener
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(job: GithubJob) {
        binding.job=job
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            onJobActionListener.onJobClicked(JobListContract.Event.OnNavigateToDetail,job);

        }
        binding.saveJob.setOnClickListener {
            if(job.isSaved!!)
            onJobActionListener.onJobClicked(JobListContract.Event.OnUnFavourite(jobID = job.id),job)
            else
                onJobActionListener.onJobClicked(JobListContract.Event.OnFavourite(jobID = job.id),job)

        }




    }


}