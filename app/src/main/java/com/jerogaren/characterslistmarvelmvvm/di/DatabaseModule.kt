package com.jerogaren.characterslistmarvelmvvm.di

import android.app.Application
import androidx.room.Room
import com.jerogaren.characterslistmarvelmvvm.db.CharactersDAO
import com.jerogaren.characterslistmarvelmvvm.db.CharactersDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): CharactersDatabase{
        return Room.databaseBuilder(application, CharactersDatabase::class.java, "CharactersDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCharactersDao(database: CharactersDatabase): CharactersDAO {
        return  database.charactersDAO
    }

    single { provideDatabase(androidApplication()) }
    single { provideCharactersDao(get()) }

}