package com.jerogaren.characterslistmarvelmvvm.repository

import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData
import com.jerogaren.characterslistmarvelmvvm.util.ResultApp

interface CharactersRepository {
    suspend fun getAllCharacters() : ResultApp<List<CharacterData>>
}