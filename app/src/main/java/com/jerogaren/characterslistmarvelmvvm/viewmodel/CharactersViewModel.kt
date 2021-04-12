package com.jerogaren.characterslistmarvelmvvm.viewmodel

import android.util.Log
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

    companion object{
        const val MTAG = "CharactersViewModel"
    }

    val showLoading = ObservableBoolean()
    val charactersList = MutableLiveData<List<CharacterData>>()
    val showError = SingleLiveEvent<String>()

    private var offset = 0
    private val limit = 20

    fun getMoreCharacters() {
        showLoading.set(true)
        viewModelScope.launch {
            val result =  repository.getMoreCharacters(offset, limit)
            showLoading.set(false)
            when (result) {
                is ResultApp.Success -> {
                    charactersList.value = result.success.data.results.toMutableList()
                    offset += limit
                    Log.d(MTAG, "offset: "+offset)
                }
                is ResultApp.Error -> showError.value = result.exception.message
            }
        }
    }
}