package com.jerogaren.characterslistmarvelmvvm.common.viewmodel.livedata

import androidx.lifecycle.MutableLiveData
import com.jerogaren.characterslistmarvelmvvm.common.viewmodel.CallResult

interface LiveDataProvider {
    fun <T> liveDataInstance(): MutableLiveData<CallResult<T>>

}

interface LiveDataObserver {

    fun <T> MutableLiveData<T>.notifyObserver()
}