package com.vince.mercadolibre.domain.usecases

import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.domain.models.ItemsWithPagination
import com.vince.mercadolibre.domain.repositories.ItemRepository
import com.vince.mercadolibre.utils.ConstantsHelper.ITEMS_TO_REQUEST

class GetItemsByCategoryUseCase(private val itemRepository: ItemRepository) {

    suspend operator fun invoke(category: String) : CallResult<ItemsWithPagination<Item>> =
        itemRepository.getItemsByCategory(category, ITEMS_TO_REQUEST)
}
