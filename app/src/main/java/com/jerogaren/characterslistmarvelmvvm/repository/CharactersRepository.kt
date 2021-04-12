package com.jerogaren.characterslistmarvelmvvm.repository

import com.jerogaren.characterslistmarvelmvvm.db.model.ResponseApi
import com.jerogaren.characterslistmarvelmvvm.util.ResultApp

interface CharactersRepository {
    suspend fun getMoreCharacters(offset: Int, limit: Int) : ResultApp<ResponseApi>
}