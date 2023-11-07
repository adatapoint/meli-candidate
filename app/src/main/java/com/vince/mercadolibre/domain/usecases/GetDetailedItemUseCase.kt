package com.vince.mercadolibre.domain.usecases

import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.domain.models.DetailedItem
import com.vince.mercadolibre.domain.repositories.ItemsRepository

class GetDetailedItemUseCase(private val itemsRepository: ItemsRepository) {

    suspend operator fun invoke(itemId: String) : CallResult<DetailedItem> =
        itemsRepository.getDetailedItem(itemId)
}
