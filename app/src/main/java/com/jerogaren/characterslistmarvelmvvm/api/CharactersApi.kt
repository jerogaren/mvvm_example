package com.jerogaren.characterslistmarvelmvvm.api

import com.jerogaren.characterslistmarvelmvvm.db.model.ResponseApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {
    @GET("characters")
    suspend fun getAllCharacters(@Query("offset") offset: Int? = 0): Response<ResponseApi>
}