package com.vince.mercadolibre.data.services

import com.vince.mercadolibre.domain.models.DetailedItem
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.domain.models.ItemsWithPagination
import com.vince.mercadolibre.remote.ApiService
import retrofit2.Response

class ItemService(private val apiService: ApiService) {

    suspend fun getItems(query: String): Response<ItemsWithPagination<Item>> =
        apiService.getItems(query)

    suspend fun getItemsByCategory(categoryId: String): Response<ItemsWithPagination<Item>> =
        apiService.getItemsByCategory(categoryId)

    suspend fun getDetailedItem(itemId: String): Response<DetailedItem> =
        apiService.getDetailedItem(itemId)
}
