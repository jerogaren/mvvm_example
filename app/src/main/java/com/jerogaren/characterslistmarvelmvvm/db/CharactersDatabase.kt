package com.jerogaren.characterslistmarvelmvvm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData

@Database(
    entities = [CharacterData::class],
    version = 1,
    exportSchema = false
)

abstract class CharactersDatabase : RoomDatabase() {
    abstract val charactersDAO: CharactersDAO
}