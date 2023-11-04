package com.vince.mercadolibre.remote

import com.vince.mercadolibre.domain.models.DetailedItem
import com.vince.mercadolibre.domain.models.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

@JvmSuppressWildcards
interface APIService {

    @GET("/sites/{SITE_ID}/search?q={query}")
    suspend fun getItems(@Path("query") query: String): Response<List<Item>>

    @GET("/sites/{SITE_ID}/search?q={query}")
    suspend fun getDetailedItem(@Path("id") id: Int): Response<DetailedItem>


}
