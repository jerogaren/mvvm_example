package com.jerogaren.characterslistmarvelmvvm.common.viewmodel.livedata

import androidx.lifecycle.MutableLiveData
import com.jerogaren.characterslistmarvelmvvm.common.viewmodel.CallResult

class DefaultLiveDataProvider : LiveDataProvider {
    override fun <T> liveDataInstance(): MutableLiveData<CallResult<T>> = MutableLiveData()

}