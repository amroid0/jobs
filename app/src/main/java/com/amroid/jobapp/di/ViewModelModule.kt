package com.amroid.jobapp.di
import com.amroid.jobapp.ui.job_detail.JobDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.amroid.jobapp.ui.job_list.JobListViewModel

    val viewModelModule = module {
        viewModel {
            JobListViewModel(repo = get())
        }
       viewModel {
            JobDetailViewModel(get())
        }
    }
