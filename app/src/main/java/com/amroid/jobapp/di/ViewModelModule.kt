package com.amroid.jobapp.di
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.amroid.jobapp.ui.job_list.JobListViewModel

    val viewModelModule = module {
        viewModel {
            JobListViewModel(repo = get())
        }
      /*  viewModel {
            PostDetailsViewModel(get())
        }*/
    }
