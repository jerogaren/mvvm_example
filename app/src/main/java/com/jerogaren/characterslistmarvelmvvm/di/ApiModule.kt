package com.jerogaren.characterslistmarvelmvvm.di

import com.jerogaren.characterslistmarvelmvvm.api.CharactersApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideCharactersApi(retrofit: Retrofit): CharactersApi{
        return retrofit.create(CharactersApi::class.java)
    }

    single { provideCharactersApi(get()) }
}