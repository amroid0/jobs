package com.amroid.jobapp.ui.job_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.amroid.jobapp.R
import com.amroid.jobapp.data.model.GithubJob
import com.amroid.jobapp.databinding.ListJobItemRowBinding
import com.amroid.jobapp.utils.OnJobActionListener


class JobListAdapter(
    private val onJobActionListener: OnJobActionListener
) : ListAdapter<GithubJob, JobViewHolder>(PositionDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):JobViewHolder {

       val binidng= DataBindingUtil.inflate<ListJobItemRowBinding>(LayoutInflater.from(parent.context),
           R.layout.list_job_item_row, parent, false)
      return  JobViewHolder(
            binidng,
            onJobActionListener
        )


    }
    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}