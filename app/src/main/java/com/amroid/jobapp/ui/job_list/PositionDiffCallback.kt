package com.amroid.jobapp.ui.job_list

import androidx.recyclerview.widget.DiffUtil
import com.amroid.jobapp.data.model.GithubJob

object PositionDiffCallback : DiffUtil.ItemCallback<GithubJob>() {

    override fun areItemsTheSame(oldItem: GithubJob, newItem: GithubJob) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: GithubJob, newItem: GithubJob) =
        oldItem == newItem
}