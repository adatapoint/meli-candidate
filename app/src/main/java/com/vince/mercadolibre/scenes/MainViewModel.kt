package com.vince.mercadolibre.scenes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.domain.models.ItemsWithPagination
import com.vince.mercadolibre.domain.models.Suggestion
import com.vince.mercadolibre.domain.usecases.GetItemsByCategoryUseCase
import com.vince.mercadolibre.domain.usecases.GetItemsByQueryUseCase
import com.vince.mercadolibre.domain.usecases.suggestion.GetSuggestionsQueriesUseCase

class MainViewModel(
    private val getItemsByQueryUseCase: GetItemsByQueryUseCase,
    private val getItemsByCategoryUseCase: GetItemsByCategoryUseCase,
    private val getSuggestionsQueriesUseCase: GetSuggestionsQueriesUseCase
) : ViewModel() {

    fun getItems(query: String): LiveData<CallResult<ItemsWithPagination<Item>>> =
        liveData { emit(getItemsByQueryUseCase(query)) }

    fun getItemsByCategory(categoryId: String): LiveData<CallResult<ItemsWithPagination<Item>>> =
        liveData { emit(getItemsByCategoryUseCase(categoryId)) }

    fun getSuggestionsQueries(query: String): LiveData<CallResult<List<Suggestion>>> =
        liveData { emit(getSuggestionsQueriesUseCase(query)) }
}
