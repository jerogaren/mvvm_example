package com.jerogaren.characterslistmarvelmvvm.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterDetail
import com.jerogaren.characterslistmarvelmvvm.repository.CharactersDetailRepository
import com.jerogaren.characterslistmarvelmvvm.util.ResultApp
import com.jerogaren.characterslistmarvelmvvm.util.SingleLiveEvent
import kotlinx.coroutines.launch

class CharacterDetailViewModel (private val repository: CharactersDetailRepository) : ViewModel() {
    companion object {
        const val MTAG = "CharacterDetailViewModel"
    }

    val showLoading = ObservableBoolean()
    val listCharacterDetail = MutableLiveData<List<CharacterDetail>>()
    val showError = SingleLiveEvent<String>()

    fun getCharacterDetail(id: Int){
        showLoading.set(true)
        viewModelScope.launch {
            val result = repository.getCharacterDetails(id)
            showLoading.set(false)
            when (result) {
                is ResultApp.Success -> {
                    listCharacterDetail.value = result.success.data.results.toMutableList()
                }
                is ResultApp.Error -> showError.value = result.exception.message
            }
        }
    }
}