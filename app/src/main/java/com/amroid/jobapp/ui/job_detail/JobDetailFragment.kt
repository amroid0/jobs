package com.amroid.jobapp.ui.job_detail

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
import com.amroid.jobapp.databinding.FragmentJobDetailBinding
import com.amroid.jobapp.databinding.FragmentJobListBinding
import com.amroid.jobapp.ui.job_list.JobListAdapter
import com.amroid.jobapp.ui.job_list.JobListContract
import com.amroid.jobapp.ui.job_list.JobListViewModel
import com.amroid.jobapp.utils.NetworkState
import com.amroid.jobapp.utils.OnPositionActionListener
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.compat.ViewModelCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JobDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JobDetailFragment : Fragment() {

    private val viewModel by viewModel<JobDetailViewModel>()
    private lateinit var binding: FragmentJobDetailBinding


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
        viewModel.setEvent(JobDetailContract.Event.OnFetchJobDetail())


    }

}