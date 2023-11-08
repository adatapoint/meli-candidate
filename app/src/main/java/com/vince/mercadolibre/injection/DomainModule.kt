package com.vince.mercadolibre.injection

import com.vince.mercadolibre.data.repositories.ItemRepositoryImpl
import com.vince.mercadolibre.domain.repositories.ItemRepository
import com.vince.mercadolibre.domain.usecases.GetDetailedItemUseCase
import com.vince.mercadolibre.domain.usecases.GetItemsByCategoryUseCase
import com.vince.mercadolibre.domain.usecases.GetItemsByQueryUseCase
import org.koin.dsl.module

val domainModule = module {

    // Repositories
    single<ItemRepository> { ItemRepositoryImpl(get()) }

    // UseCases
    single { GetItemsByQueryUseCase(get()) }
    single { GetItemsByCategoryUseCase(get()) }
    single { GetDetailedItemUseCase(get()) }
}
