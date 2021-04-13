package com.jerogaren.characterslistmarvelmvvm.di

import android.content.Context
import com.jerogaren.characterslistmarvelmvvm.api.CharactersApi
import com.jerogaren.characterslistmarvelmvvm.db.CharactersDAO
import com.jerogaren.characterslistmarvelmvvm.repository.CharactersDetailRepository
import com.jerogaren.characterslistmarvelmvvm.repository.CharactersDetailsRepositoryImpl
import com.jerogaren.characterslistmarvelmvvm.repository.CharactersRepository
import com.jerogaren.characterslistmarvelmvvm.repository.CharactersRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun provideCharactersRepository(api: CharactersApi, context: Context, dao : CharactersDAO): CharactersRepository {
        return CharactersRepositoryImpl(api, context, dao)
    }
    fun provideCharactersDetailsRepository(api: CharactersApi, context: Context): CharactersDetailRepository {
        return CharactersDetailsRepositoryImpl(api, context)
    }
    single { provideCharactersRepository(get(), androidContext(), get()) }
    single { provideCharactersDetailsRepository(get(), androidContext())}

}