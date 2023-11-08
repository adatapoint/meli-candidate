package com.vince.mercadolibre.domain.usecases

import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.domain.models.DetailedItem
import com.vince.mercadolibre.domain.repositories.ItemRepository

class GetDetailedItemUseCase(private val itemRepository: ItemRepository) {

    suspend operator fun invoke(itemId: String) : CallResult<DetailedItem> =
        itemRepository.getDetailedItem(itemId)
}
