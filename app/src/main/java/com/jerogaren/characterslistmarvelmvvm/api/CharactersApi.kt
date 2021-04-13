package com.jerogaren.characterslistmarvelmvvm.api

import com.jerogaren.characterslistmarvelmvvm.db.model.ResponseApi
import com.jerogaren.characterslistmarvelmvvm.db.model.ResponseDetailApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {
    @GET("characters")
    suspend fun getAllCharacters(@Query("offset") offset: Int? = 0,
    @Query("limit") limit: Int? = 20): Response<ResponseApi>

    @GET("characters/{id}")
    suspend fun getCharacterDetail(@Path("id") id: Int): Response<ResponseDetailApi>
}