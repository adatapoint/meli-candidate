package com.vince.mercadolibre.injection

import com.vince.mercadolibre.data.repositories.ItemsRepositoryImpl
import com.vince.mercadolibre.data.repositories.SearchRepositoryImpl
import com.vince.mercadolibre.domain.repositories.ItemsRepository
import com.vince.mercadolibre.domain.repositories.SearchRepository
import com.vince.mercadolibre.domain.usecases.GetItemsByCategoryUseCase
import com.vince.mercadolibre.domain.usecases.GetItemsUseCase
import com.vince.mercadolibre.domain.usecases.suggestion.GetSuggestionsQueriesUseCase
import org.koin.dsl.module

val domainModule = module {

    // Repositories
    single<ItemsRepository> { ItemsRepositoryImpl(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }

    // UseCases
    single { GetItemsUseCase(get()) }
    single { GetItemsByCategoryUseCase(get()) }
    single { GetSuggestionsQueriesUseCase(get()) }
}
