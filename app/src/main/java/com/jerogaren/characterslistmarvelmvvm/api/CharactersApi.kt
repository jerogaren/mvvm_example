package com.jerogaren.characterslistmarvelmvvm.api

import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {
    @GET("/api/v1")
    suspend fun getAllCharacters(): Response<List<CharacterData>>
}