package com.jerogaren.characterslistmarvelmvvm.common.processor

interface ViewProcessor<V> {

    fun attachView(view: V)

    fun detachView()
}