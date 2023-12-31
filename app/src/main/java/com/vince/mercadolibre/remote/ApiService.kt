package com.vince.mercadolibre.remote

import com.vince.mercadolibre.domain.models.DetailedItem
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.domain.models.ItemsWithPagination
import com.vince.mercadolibre.utils.ConstantsHelper.SITE_ID_COLOMBIA
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@JvmSuppressWildcards
interface ApiService {

    @GET("/sites/$SITE_ID_COLOMBIA/search")
    suspend fun getItemsByQuery(
        @Query("q") query: String,
        @Query("limit") limit: Int
    ): Response<ItemsWithPagination<Item>>

    @GET("/sites/$SITE_ID_COLOMBIA/search")
    suspend fun getItemsByCategory(
        @Query("category") categoryId: String,
        @Query("limit") limit: Int
    ): Response<ItemsWithPagination<Item>>

    @GET("/items/{ids}")
    suspend fun getDetailedItem(@Path("ids") id: String): Response<DetailedItem>
}
