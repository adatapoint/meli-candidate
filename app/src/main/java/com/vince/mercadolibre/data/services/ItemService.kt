package com.vince.mercadolibre.data.services

import com.vince.mercadolibre.domain.models.DetailedItem
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.domain.models.ItemsWithPagination
import com.vince.mercadolibre.remote.ApiService
import retrofit2.Response

class ItemService(private val apiService: ApiService) {

    suspend fun getItemsByQuery(query: String, limit: Int): Response<ItemsWithPagination<Item>> =
        apiService.getItems(query, limit)

    suspend fun getItemsByCategory(categoryId: String, limit: Int): Response<ItemsWithPagination<Item>> =
        apiService.getItemsByCategory(categoryId, limit)

    suspend fun getDetailedItem(itemId: String): Response<DetailedItem> =
        apiService.getDetailedItem(itemId)
}
