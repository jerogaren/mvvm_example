package com.jerogaren.characterslistmarvelmvvm.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData

@Dao
interface CharactersDAO {

    @Query("SELECT * FROM CharactersData")
    fun getAllCharacters(): List<CharacterData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(characters: List<CharacterData>)

}