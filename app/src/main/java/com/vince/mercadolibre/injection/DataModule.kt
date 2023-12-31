package com.vince.mercadolibre.injection

import com.vince.mercadolibre.BuildConfig
import com.vince.mercadolibre.data.services.ItemService
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

    // Services
    single { createAPIService(get()) }
    single { ItemService(get()) }
}

fun createAPIService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
