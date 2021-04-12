package com.jerogaren.characterslistmarvelmvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.jerogaren.characterslistmarvelmvvm.repository.CharactersRepository
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData
import com.jerogaren.characterslistmarvelmvvm.util.ResultApp
import com.jerogaren.characterslistmarvelmvvm.util.SingleLiveEvent
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: CharactersRepository) : ViewModel(){

    val showLoading = ObservableBoolean()
    val charactersList = MutableLiveData<List<CharacterData>>()
    val showError = SingleLiveEvent<String>()

    fun getAllCharacters() {
        showLoading.set(true)
        viewModelScope.launch {
            val result =  repository.getAllCharacters()

            showLoading.set(false)
            when (result) {
                is ResultApp.Success -> {
                    charactersList.value = result.success.data.results.toMutableList()
                }
                is ResultApp.Error -> showError.value = result.exception.message
            }
        }
    }
}