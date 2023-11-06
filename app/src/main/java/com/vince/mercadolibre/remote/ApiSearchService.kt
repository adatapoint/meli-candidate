package com.vince.mercadolibre.remote

import com.vince.mercadolibre.domain.models.SuggestionsResponse
import com.vince.mercadolibre.utils.ConstantsHelper.SITE_ID_COLOMBIA
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

@JvmSuppressWildcards
interface ApiSearchService {

    @GET("/resources/sites/$SITE_ID_COLOMBIA/autosuggest?showFilters=true&limit=6&api_version=2")
    suspend fun getSuggestedQueries(@Query("q") query: String): Response<SuggestionsResponse>

}
