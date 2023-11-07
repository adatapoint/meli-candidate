package com.vince.mercadolibre.domain.usecases

import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.domain.models.ItemsWithPagination
import com.vince.mercadolibre.domain.repositories.ItemsRepository
import com.vince.mercadolibre.utils.ConstantsHelper.ITEMS_TO_REQUEST

class GetItemsByQueryUseCase(private val itemsRepository: ItemsRepository) {

    suspend operator fun invoke(query: String) : CallResult<ItemsWithPagination<Item>> =
        itemsRepository.getItemsByQuery(query, ITEMS_TO_REQUEST)
}
