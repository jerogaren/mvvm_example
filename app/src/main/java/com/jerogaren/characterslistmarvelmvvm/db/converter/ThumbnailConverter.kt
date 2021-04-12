package com.jerogaren.characterslistmarvelmvvm.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jerogaren.characterslistmarvelmvvm.db.model.Thumbnail

class ThumbnailConverter {

    @TypeConverter
    fun stringToThumbnail(json: String): Thumbnail? {
        val gson = Gson()
        val type = object : TypeToken<Thumbnail>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun thumbnailToString(thumbnail: Thumbnail): String {
        val gson = Gson()
        val type = object : TypeToken<Thumbnail>() {}.type
        return gson.toJson(thumbnail, type)
    }
}