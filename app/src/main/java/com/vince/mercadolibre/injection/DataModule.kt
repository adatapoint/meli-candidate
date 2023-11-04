package com.vince.mercadolibre.injection

import com.vince.mercadolibre.BuildConfig
import com.vince.mercadolibre.data.services.ItemService
import com.vince.mercadolibre.remote.APIService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    // Client
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Services
    single { createAPIService(get()) }
    single { ItemService(get()) }
}

fun createAPIService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)
