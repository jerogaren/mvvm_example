package com.jerogaren.characterslistmarvelmvvm.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData

class CharacterDataCoverter {

    @TypeConverter
    fun stringToCharacterData(json: String): CharacterData? {
        val gson = Gson()
        val type = object : TypeToken<CharacterData>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun characterDataToString(characterData: CharacterData): String {
        val gson = Gson()
        val type = object : TypeToken<CharacterData>() {}.type
        return gson.toJson(characterData, type)
    }
}