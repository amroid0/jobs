package com.amroid.jobapp.ui.job_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.amroid.jobapp.R
import com.amroid.jobapp.data.model.GithubJob
import com.amroid.jobapp.databinding.FragmentJobDetailBinding
import com.amroid.jobapp.utils.NetworkState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel


class JobDetailFragment : Fragment() {

    private val viewModel by viewModel<JobDetailViewModel>()
    private lateinit var binding: FragmentJobDetailBinding
    val args: JobDetailFragmentArgs by navArgs()
    var job :GithubJob?=null


    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObservers()

    }

    /**
     * Initialize Observers
     */
    @InternalCoroutinesApi
    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (it.jobDetailState) {


                    is NetworkState.Success -> {
                        job=it.jobDetailState.data;
                        binding.job=job
                        binding.executePendingBindings()

                    }
                }
            }
        }

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_job_detail, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setEvent(JobDetailContract.Event.OnFetchJobDetail(args.jobID))
          binding.header.favImg.setOnClickListener {
              onFavAction(job?.isSaved!!)
          }


    }
   fun onFavAction(isFav:Boolean){
       if (!isFav)
           viewModel.setEvent(JobDetailContract.Event.OnFavourite(args.jobID))
       else
           viewModel.setEvent(JobDetailContract.Event.OnUnFavourite(args.jobID))

   }

}