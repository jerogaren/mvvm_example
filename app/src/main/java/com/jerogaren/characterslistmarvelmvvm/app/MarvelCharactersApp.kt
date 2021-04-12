package com.jerogaren.characterslistmarvelmvvm.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelCharactersApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MarvelCharactersApp)
        }
    }
}