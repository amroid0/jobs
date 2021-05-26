package com.amroid.jobapp.ui.job_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.amroid.jobapp.R
import com.amroid.jobapp.data.model.GithubJob
import com.amroid.jobapp.databinding.FragmentJobListBinding
import com.amroid.jobapp.utils.NetworkState
import com.amroid.jobapp.utils.OnJobActionListener
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

class JobListFragment : Fragment(),
    OnJobActionListener {

    private val viewModel by viewModel<JobListViewModel>()
    private lateinit var binding: FragmentJobListBinding
    private var jobId: String?=null

    private val adapter = JobListAdapter(this)

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObservers()

    }
     private  fun setupRecycle(){
         binding.recyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
         binding.recyclerView.hasFixedSize()
         binding.recyclerView.adapter=adapter;
     }
    /**
     * Initialize Observers
     */
    @InternalCoroutinesApi
    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {

                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
                binding.noResultsMessage.visibility = View.GONE
                when (it.jobListState) {


                    is NetworkState.Idle -> {
                    }
                    is NetworkState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is NetworkState.Success -> {
                        if(binding.swipeRefresh.isRefreshing){
                            binding.swipeRefresh.isRefreshing=false
                        }
                        binding.recyclerView.visibility = View.VISIBLE
                        adapter.submitList(it.jobListState.data)

                    }

                    is NetworkState.Error -> {
                        binding.noResultsMessage.visibility = View.VISIBLE

                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.effect.collect {
                when (it) {
                    is JobListContract.Effect.NavigateToDetail -> {
                        val action=JobListFragmentDirections.actionJobListFragmentToJobDetailFragment(jobId!!);
                         findNavController().navigate(action)
                    }
                }
            }
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate( inflater,R.layout.fragment_job_list, container, false)
    return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycle()
        viewModel.setEvent(JobListContract.Event.OnFetchJob)
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.setEvent(JobListContract.Event.OnFetchJob)
        }


    }

    override fun onJobClicked(positionAction: JobListContract.Event, job:GithubJob) {
        jobId=job.id
        viewModel.setEvent(positionAction)
    }



}