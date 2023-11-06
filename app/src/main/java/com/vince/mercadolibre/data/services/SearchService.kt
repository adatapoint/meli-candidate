package com.vince.mercadolibre.data.services

import com.vince.mercadolibre.domain.models.SuggestionsResponse
import com.vince.mercadolibre.remote.ApiSearchService
import retrofit2.Response

class SearchService(private val apiSearchService: ApiSearchService) {

    suspend fun getSuggestedQueries(query: String): Response<SuggestionsResponse> =
        apiSearchService.getSuggestedQueries(query)

}
