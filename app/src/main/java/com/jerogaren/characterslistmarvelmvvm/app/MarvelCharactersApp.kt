package com.jerogaren.characterslistmarvelmvvm.app

import android.app.Application
import com.jerogaren.characterslistmarvelmvvm.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelCharactersApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            //androidLogger()
            androidContext(this@MarvelCharactersApp)
            modules(
                apiModule,
                viewModelModule,
                repositoryModule,
                networkModule,
                databaseModule
            )
        }
    }
}