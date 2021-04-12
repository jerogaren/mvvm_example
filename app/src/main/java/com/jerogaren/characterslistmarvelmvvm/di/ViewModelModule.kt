package com.jerogaren.characterslistmarvelmvvm.di

import com.jerogaren.characterslistmarvelmvvm.viewmodel.CharactersViewModel
import com.jerogaren.characterslistmarvelmvvm.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        CharactersViewModel(repository = get())
    }
    viewModel {
        MainActivityViewModel()
    }

}