package com.jerogaren.characterslistmarvelmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    val toolbarTitle = MutableLiveData<String>()
}