package com.vince.mercadolibre.injection

import com.vince.mercadolibre.BuildConfig
import com.vince.mercadolibre.data.services.ItemService
import com.vince.mercadolibre.data.services.SearchService
import com.vince.mercadolibre.remote.ApiSearchService
import com.vince.mercadolibre.remote.ApiService
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TIME_OUT = 30L

val dataModule = module {
    // Client

    single {
        OkHttpClient
            .Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//  TODO for requesting suggestions as the user starts typing
//    single {
//        Retrofit.Builder()
//            .baseUrl(BuildConfig.API_SEARCH_SUGGESTIONS_URL)
//            .client(get())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

    // Services
    single { createAPIService(get()) }
    single { createAPISearchService(get()) }
    single { ItemService(get()) }
    single { SearchService(get()) }
}

fun createAPIService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

fun createAPISearchService(retrofit: Retrofit): ApiSearchService =
    retrofit.create(ApiSearchService::class.java)
