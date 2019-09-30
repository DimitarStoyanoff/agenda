/*
 * All rights reserved.
 */

package com.stoyanoff.agenda.inject

import com.stoyanoff.agenda.presentation.home.EventsAdapter
import com.stoyanoff.agenda.presentation.home.HomeViewModel
import com.stoyanoff.agenda.presentation.home.HomeViewState
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by L on 29/09/2019.
 *  All rights reserved.
 */
val presentationModule = module {
    viewModel { HomeViewModel(get(), get()) }
    factory { HomeViewState() }
    factory { EventsAdapter() }

}