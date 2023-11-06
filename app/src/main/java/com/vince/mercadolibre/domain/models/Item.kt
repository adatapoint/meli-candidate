package com.vince.mercadolibre.domain.models

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("thumbnail")
    val image: String,
    @SerializedName("currency_id")
    val currency: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("condition")
    val condition: String
)

data class DetailedItem(
    val id: String,
    val name: String,
    val description: String
)
