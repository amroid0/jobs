package com.amroid.jobapp.ui.job_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.amroid.jobapp.R
import com.amroid.jobapp.databinding.FragmentJobListBinding
import com.amroid.jobapp.utils.NetworkState
import com.amroid.jobapp.utils.OnPositionActionListener
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

class JobListFragment : Fragment(),
    OnPositionActionListener {

    private val viewModel by viewModel<JobListViewModel>()
    private lateinit var binding: FragmentJobListBinding

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
                when (it.jobListState) {

                    is NetworkState.Idle -> {
                        binding.progressBar.visibility = View.GONE
                    }
                    is NetworkState.Loading -> {                         binding.progressBar.visibility = View.VISIBLE
                    }
                    is NetworkState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        showToast(it.jobListState.data.toString())
                        adapter.submitList(it.jobListState.data)

                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.effect.collect {
                when (it) {
                    is JobListContract.Effect.NavigateToDetail -> {
                         findNavController().navigate(R.id.action_jobListFragment_to_jobDetailFragment)
                    }
                }
            }
        }
    }


    /**
     * Show simple toast message
     */
    private fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
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


    }

    override fun onPositionAction(positionAction: JobListContract.Event) {
        viewModel.setEvent(JobListContract.Event.OnNavigateToDetail)

    }


}