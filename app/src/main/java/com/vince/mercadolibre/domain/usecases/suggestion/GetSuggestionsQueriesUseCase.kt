package com.vince.mercadolibre.domain.usecases.suggestion

import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.domain.models.Suggestion
import com.vince.mercadolibre.domain.repositories.SearchRepository

class GetSuggestionsQueriesUseCase(private val searchRepository: SearchRepository) {

    suspend operator fun invoke(query: String) : CallResult<List<Suggestion>> =
        searchRepository.getSuggestedQueries(query)
}
