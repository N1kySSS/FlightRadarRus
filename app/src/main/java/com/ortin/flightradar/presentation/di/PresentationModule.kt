package com.ortin.flightradar.presentation.di

import com.ortin.flightradar.presentation.viewmodel.LocationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val providePresentationModule = module {

    viewModel<LocationViewModel> { LocationViewModel() }
}
