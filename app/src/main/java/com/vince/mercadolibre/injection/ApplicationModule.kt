package com.vince.mercadolibre.injection

import com.vince.mercadolibre.scenes.MainViewModel
import com.vince.mercadolibre.scenes.detaileditem.DetailedItemViewModel
import com.vince.mercadolibre.scenes.items.ItemsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {

    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { DetailedItemViewModel(get()) }
    viewModel { ItemsViewModel(get(), get()) }
}
