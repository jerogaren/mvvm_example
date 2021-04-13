package com.jerogaren.characterslistmarvelmvvm.repository

import com.jerogaren.characterslistmarvelmvvm.db.model.ResponseDetailApi
import com.jerogaren.characterslistmarvelmvvm.util.ResultApp

interface CharactersDetailRepository {
    suspend fun getCharacterDetails(id: Int) : ResultApp<ResponseDetailApi>
}