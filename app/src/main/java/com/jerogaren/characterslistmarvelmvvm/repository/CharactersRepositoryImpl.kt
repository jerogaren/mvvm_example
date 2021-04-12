package com.jerogaren.characterslistmarvelmvvm.repository

import android.content.Context
import android.util.Log
import com.jerogaren.characterslistmarvelmvvm.NetworkManager.isOnline
import com.jerogaren.characterslistmarvelmvvm.api.CharactersApi
import com.jerogaren.characterslistmarvelmvvm.db.CharactersDAO
import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData
import com.jerogaren.characterslistmarvelmvvm.db.model.Data
import com.jerogaren.characterslistmarvelmvvm.db.model.ResponseApi
import com.jerogaren.characterslistmarvelmvvm.util.ResultApp
import com.jerogaren.characterslistmarvelmvvm.util.Utils.handleApiError
import com.jerogaren.characterslistmarvelmvvm.util.Utils.handleSuccess
import com.jerogaren.characterslistmarvelmvvm.util.noNetworkConnectivityError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersRepositoryImpl(
    private val api: CharactersApi,
    private val context: Context,
    private val dao: CharactersDAO
) : CharactersRepository {
    companion object {
        const val TAG = "CharactersRepository"
    }

    override suspend fun getMoreCharacters(offset: Int, limit: Int): ResultApp<ResponseApi> {
        if (isOnline(context)) {
            return try {
                val response = api.getAllCharacters(offset, limit)
                if (response.isSuccessful) {
                    //save the data
                    response.body()?.let {
                        withContext(Dispatchers.IO) { dao.add(it.data.results) }
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
            val listCharacterData = getCountriesDataFromDB()
            val responseApi = ResponseApi(
                0, "", Data(0, 0, 0, 0, listCharacterData)
            )

            return if (listCharacterData.isNotEmpty()) {
                Log.d(TAG, "from db")
                ResultApp.Success(responseApi)
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