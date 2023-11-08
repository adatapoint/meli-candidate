package com.vince.mercadolibre.domain.usecases

import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.domain.models.ItemsWithPagination
import com.vince.mercadolibre.domain.repositories.ItemRepository
import com.vince.mercadolibre.utils.ConstantsHelper.ITEMS_TO_REQUEST

class GetItemsByQueryUseCase(private val itemRepository: ItemRepository) {

    suspend operator fun invoke(query: String) : CallResult<ItemsWithPagination<Item>> =
        itemRepository.getItemsByQuery(query, ITEMS_TO_REQUEST)
}
