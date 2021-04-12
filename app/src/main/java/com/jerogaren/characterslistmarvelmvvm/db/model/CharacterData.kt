package com.jerogaren.characterslistmarvelmvvm.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

class CharacterData {

    @Entity(tableName = "Characters")
    @Parcelize
    class CountriesData (
        @PrimaryKey(autoGenerate = true) val id: Int,
        val name: String?,
        val alias: String?
    ) : Parcelable


}