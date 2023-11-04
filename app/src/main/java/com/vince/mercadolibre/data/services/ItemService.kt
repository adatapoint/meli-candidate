package com.vince.mercadolibre.data.services

import com.vince.mercadolibre.domain.models.DetailedItem
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.remote.APIService
import retrofit2.Response

class ItemService(private val apiService: APIService) {

    suspend fun getItems(query: String): Response<List<Item>> = apiService.getItems(query)

    suspend fun getDetailedItem(itemId: Int): Response<DetailedItem> = apiService.getDetailedItem(itemId)
}