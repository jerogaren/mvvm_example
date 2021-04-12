package com.jerogaren.characterslistmarvelmvvm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jerogaren.characterslistmarvelmvvm.db.converter.CharacterDataCoverter
import com.jerogaren.characterslistmarvelmvvm.db.converter.ThumbnailConverter
import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData

@Database(
    entities = [CharacterData::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(
    CharacterDataCoverter::class,
    ThumbnailConverter::class
)

abstract class CharactersDatabase : RoomDatabase() {
    abstract val charactersDAO: CharactersDAO
}