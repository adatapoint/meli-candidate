package com.vince.mercadolibre.injection

import com.vince.mercadolibre.data.repositories.ItemsRepositoryImpl
import com.vince.mercadolibre.domain.repositories.ItemsRepository
import com.vince.mercadolibre.domain.usecases.GetItemsByCategoryUseCase
import com.vince.mercadolibre.domain.usecases.GetItemsUseCase
import org.koin.dsl.module

val domainModule = module {

    // Repositories
    single<ItemsRepository> { ItemsRepositoryImpl(get()) }

    // UseCases
    single { GetItemsUseCase(get()) }
    single { GetItemsByCategoryUseCase(get()) }
}
