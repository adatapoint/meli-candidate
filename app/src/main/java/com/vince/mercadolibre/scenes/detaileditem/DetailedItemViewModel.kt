package com.vince.mercadolibre.scenes.detaileditem

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.domain.models.DetailedItem
import com.vince.mercadolibre.domain.usecases.GetDetailedItemUseCase

class DetailedItemViewModel(
    private val getDetailedItemUseCase: GetDetailedItemUseCase
): ViewModel()  {

    fun getDetailedItem(itemId: String) : LiveData<CallResult<DetailedItem>> =
        liveData { emit(getDetailedItemUseCase(itemId)) }
}