package com.vince.mercadolibre.domain.models

import com.google.gson.annotations.SerializedName

data class ItemsWithPagination<E>(
    @SerializedName("paging")
    val paging: PagingInfo,
    @SerializedName("results")
    val items: List<E>
)

data class PagingInfo(
    val total: Int,
    val limit: Int
)
