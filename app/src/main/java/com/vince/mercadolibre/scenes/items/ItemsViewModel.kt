package com.vince.mercadolibre.scenes.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.domain.models.ItemsWithPagination
import com.vince.mercadolibre.domain.usecases.GetItemsByCategoryUseCase
import com.vince.mercadolibre.domain.usecases.GetItemsByQueryUseCase

class ItemsViewModel(
    private val getItemsByCategoryUseCase: GetItemsByCategoryUseCase,
    private val getItemsByQueryUseCase: GetItemsByQueryUseCase,
) : ViewModel() {

    fun getItemsByQuery(query: String): LiveData<CallResult<ItemsWithPagination<Item>>> =
        liveData { emit(getItemsByQueryUseCase(query)) }

    fun getItemsByCategory(categoryId: String): LiveData<CallResult<ItemsWithPagination<Item>>> =
        liveData { emit(getItemsByCategoryUseCase(categoryId)) }
}
