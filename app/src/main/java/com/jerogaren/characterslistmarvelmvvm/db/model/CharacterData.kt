package com.jerogaren.characterslistmarvelmvvm.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class ResponseApi(
    val code: Int,
    val etag: String,
    val data: Data
)

data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterData>
)

@Entity(tableName = "Thumbnail")
@Parcelize
data class Thumbnail(@PrimaryKey(autoGenerate = true) val id: Int,
    val path: String,
    val extension: String
) : Parcelable

@Entity(tableName = "CharactersData")
@Parcelize
data class CharacterData(
    @PrimaryKey(autoGenerate = true) val primaryKey: Int,
    val id: Int,
    val name: String,
    val description: String?,
    val thumbnail: Thumbnail?,
) : Parcelable




