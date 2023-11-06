package com.vince.mercadolibre.domain.models

import com.google.gson.annotations.SerializedName

data class Suggestion(
    @SerializedName("q")
    private val text: String
)

data class SuggestionsResponse(
    @SerializedName("suggested_queries")
    val suggestions: List<Suggestion>
)
