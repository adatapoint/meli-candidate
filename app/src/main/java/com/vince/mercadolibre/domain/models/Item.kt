package com.vince.mercadolibre.domain.models

data class Item(
    val id: Int,
    val name: String
)

data class DetailedItem(
    val id: Int,
    val name: String,
    val description: String
)