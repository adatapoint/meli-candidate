package com.vince.mercadolibre.data.repositories

import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.data.services.SearchService
import com.vince.mercadolibre.domain.models.Suggestion
import com.vince.mercadolibre.domain.repositories.SearchRepository
import com.vince.mercadolibre.utils.handleResponse

class SearchRepositoryImpl(private val searchService: SearchService) : SearchRepository {

    override suspend fun getSuggestedQueries(query: String): CallResult<List<Suggestion>> =
        searchService.getSuggestedQueries(query).handleResponse {
            it.suggestions
        }
}
