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
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("pictures")
    val pictures: List<Picture>,
    @SerializedName("currency_id")
    val currency: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("attributes")
    val attributes: List<Attribute>,
    @SerializedName("condition")
    val condition: String
)

data class Attribute(
    @SerializedName("name")
    val name: String,
    @SerializedName("value_name")
    val value: String
)

data class Picture(
    @SerializedName("url")
    val picture: String
)
