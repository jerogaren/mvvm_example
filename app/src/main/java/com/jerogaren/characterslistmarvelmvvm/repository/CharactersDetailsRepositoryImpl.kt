package com.jerogaren.characterslistmarvelmvvm.repository

import android.content.Context
import android.util.Log
import com.jerogaren.characterslistmarvelmvvm.NetworkManager.isOnline
import com.jerogaren.characterslistmarvelmvvm.api.CharactersApi
import com.jerogaren.characterslistmarvelmvvm.db.CharactersDAO
import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData
import com.jerogaren.characterslistmarvelmvvm.db.model.Data
import com.jerogaren.characterslistmarvelmvvm.db.model.ResponseApi
import com.jerogaren.characterslistmarvelmvvm.db.model.ResponseDetailApi
import com.jerogaren.characterslistmarvelmvvm.util.ResultApp
import com.jerogaren.characterslistmarvelmvvm.util.Utils.handleApiError
import com.jerogaren.characterslistmarvelmvvm.util.Utils.handleSuccess
import com.jerogaren.characterslistmarvelmvvm.util.noNetworkConnectivityError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersDetailsRepositoryImpl(
    private val api: CharactersApi,
    private val context: Context
) : CharactersDetailRepository {
    companion object {
        const val TAG = "CharactersDetailsRepositoryImpl"
    }

    override suspend fun getCharacterDetails(id: Int): ResultApp<ResponseDetailApi> {
        return try {
            val response = api.getCharacterDetail(id)
            if (response.isSuccessful) {
                handleSuccess(response)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            ResultApp.Error(e)
        }
    }


}