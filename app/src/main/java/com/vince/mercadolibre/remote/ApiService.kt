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

    // TODO
    @GET("/sites/$SITE_ID_COLOMBIA/search")
    suspend fun getItems(@Query("q") query: String): Response<ItemsWithPagination<Item>>

    @GET("/sites/$SITE_ID_COLOMBIA/search")
    suspend fun getItemsByCategory(@Query("category") categoryId: String): Response<ItemsWithPagination<Item>>

    @GET("/items?ids={id}")
    suspend fun getDetailedItem(@Path("id") id: String): Response<DetailedItem>

}
