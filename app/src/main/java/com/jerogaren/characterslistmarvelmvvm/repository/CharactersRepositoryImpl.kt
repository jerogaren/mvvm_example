package com.jerogaren.characterslistmarvelmvvm.repository

import android.content.Context
import android.util.Log
import com.jerogaren.characterslistmarvelmvvm.NetworkManager.isOnline
import com.jerogaren.characterslistmarvelmvvm.api.CharactersApi
import com.jerogaren.characterslistmarvelmvvm.db.CharactersDAO
import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData
import com.jerogaren.characterslistmarvelmvvm.util.ResultApp
import com.jerogaren.characterslistmarvelmvvm.util.TAG
import com.jerogaren.characterslistmarvelmvvm.util.Utils.handleApiError
import com.jerogaren.characterslistmarvelmvvm.util.Utils.handleSuccess
import com.jerogaren.characterslistmarvelmvvm.util.noNetworkConnectivityError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersRepositoryImpl (
private val api: CharactersApi,
private val context: Context,
private val dao: CharactersDAO
): CharactersRepository{
    override suspend fun getAllCharacters(): ResultApp<List<CharacterData>> {
        if (isOnline(context)) {
            return try {
                val response = api.getAllCharacters()
                if (response.isSuccessful) {
                    //save the data
                    response.body()?.let {
                        withContext(Dispatchers.IO) { dao.add(it) }
                    }
                    handleSuccess(response)
                } else {
                    handleApiError(response)
                }
            } catch (e: Exception) {
                ResultApp.Error(e)
            }
        } else {
            //check in db if the data exists
            val data = getCountriesDataFromDB()
            return if (data.isNotEmpty()) {
                Log.d(TAG, "from db")
                ResultApp.Success(data)
            } else
            //no network
                context.noNetworkConnectivityError()
        }
    }

    private suspend fun getCountriesDataFromDB(): List<CharacterData> {
        return withContext(Dispatchers.IO) {
            dao.getAllCharacters()
        }
    }

}