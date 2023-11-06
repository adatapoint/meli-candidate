package com.vince.mercadolibre.domain.repositories

import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.domain.models.DetailedItem
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.domain.models.ItemsWithPagination

interface ItemsRepository {

    suspend fun getItems(query: String, limit: Int): CallResult<ItemsWithPagination<Item>>

    suspend fun getItemsByCategory(categoryId: String, limit: Int): CallResult<ItemsWithPagination<Item>>

    suspend fun getDetailedItem(itemId: String): CallResult<DetailedItem>
}