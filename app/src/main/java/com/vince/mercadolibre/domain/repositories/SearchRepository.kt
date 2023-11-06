package com.vince.mercadolibre.domain.repositories

import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.domain.models.Suggestion

interface SearchRepository {

    suspend fun getSuggestedQueries(query: String): CallResult<List<Suggestion>>

}
