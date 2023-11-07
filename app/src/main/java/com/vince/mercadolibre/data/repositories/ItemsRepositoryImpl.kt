package com.vince.mercadolibre.data.repositories

import android.util.Log
import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.data.services.ItemService
import com.vince.mercadolibre.domain.models.DetailedItem
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.domain.models.ItemsWithPagination
import com.vince.mercadolibre.domain.repositories.ItemsRepository
import com.vince.mercadolibre.utils.ConstantsHelper.LOG_TAG
import com.vince.mercadolibre.utils.handleResponse

class ItemsRepositoryImpl(private val itemService: ItemService) : ItemsRepository {

    override suspend fun getItemsByQuery(
        query: String,
        limit: Int
    ): CallResult<ItemsWithPagination<Item>> =
        itemService.getItems(query, limit).handleResponse {
            Log.d(LOG_TAG, "$it")
            val items = it.items
            val pagination = it.paging
            ItemsWithPagination(pagination, items)
        }

    override suspend fun getItemsByCategory(
        categoryId: String,
        limit: Int
    ): CallResult<ItemsWithPagination<Item>> =
        itemService.getItemsByCategory(categoryId, limit).handleResponse {
            Log.d(LOG_TAG, "$it")
            val items = it.items
            val pagination = it.paging
            ItemsWithPagination(pagination, items)
        }

    override suspend fun getDetailedItem(itemId: String): CallResult<DetailedItem> =
        itemService.getDetailedItem(itemId).handleResponse { it }
}
