package com.vince.mercadolibre.injection

import com.vince.mercadolibre.scenes.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {

    viewModel { MainViewModel(get(), get(), get()) }
}
