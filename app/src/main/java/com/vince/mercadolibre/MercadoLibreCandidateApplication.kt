package com.vince.mercadolibre

import androidx.multidex.MultiDexApplication
import com.vince.mercadolibre.injection.applicationModule
import com.vince.mercadolibre.injection.dataModule
import com.vince.mercadolibre.injection.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MercadoLibreCandidateApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startDependencyInjection()
    }

    private fun startDependencyInjection() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MercadoLibreCandidateApplication)
            modules(applicationModule, domainModule, dataModule)
        }
    }

}