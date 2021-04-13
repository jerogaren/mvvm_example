package com.jerogaren.characterslistmarvelmvvm.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterDetail
import com.jerogaren.characterslistmarvelmvvm.db.model.Items
import com.jerogaren.characterslistmarvelmvvm.repository.CharactersDetailRepository
import com.jerogaren.characterslistmarvelmvvm.util.ResultApp
import com.jerogaren.characterslistmarvelmvvm.util.SingleLiveEvent
import kotlinx.coroutines.launch

class CharacterDetailViewModel (private val repository: CharactersDetailRepository) : ViewModel() {
    companion object {
        const val MTAG = "CharacterDetailViewModel"
    }

    val showLoading = ObservableBoolean()
    val listItems = MutableLiveData<List<String>>()
    val showError = SingleLiveEvent<String>()

    fun getCharacterDetail(id: Int){
        showLoading.set(true)
        viewModelScope.launch {
            val result = repository.getCharacterDetails(id)
            showLoading.set(false)
            when (result) {
                is ResultApp.Success -> {
                    listItems.value = createListItems(result.success.data.results)
                }
                is ResultApp.Error -> showError.value = result.exception.message
            }
        }
    }

    private fun createListItems(results: List<CharacterDetail>) : List<String> {
        val mutableList = mutableListOf<String>()
        results.first().apply {
            comics?.items?.forEach {
                mutableList.add("Comics - "+it.name)
            }

            series?.items?.forEach {
                mutableList.add("Series - "+it.name)
            }

            stories?.items?.forEach {
                mutableList.add("Stories - "+it.name)
            }


            events?.items?.forEach {
                mutableList.add("Events - "+it.name)
            }
        }

        return mutableList
    }
}