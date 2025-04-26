package com.ortin.flightradar.di

import com.ortin.flightradar.domain.email.GmailSender
import com.ortin.flightradar.presentation.viewmodel.FeedbackScreenViewModel
import com.ortin.flightradar.presentation.viewmodel.MapScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val provideModule = module {

    single<GmailSender> {
        val email = get<String>(named("MY_EMAIL"))
        val password = get<String>(named("MY_PASSWORD"))
        GmailSender(email, password)
    }

    viewModel<MapScreenViewModel> { MapScreenViewModel() }

    viewModel<FeedbackScreenViewModel> { FeedbackScreenViewModel(get()) }
}
