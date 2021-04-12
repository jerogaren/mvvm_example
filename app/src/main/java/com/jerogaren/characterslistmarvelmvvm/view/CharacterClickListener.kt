package com.jerogaren.characterslistmarvelmvvm.view

import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData

interface CharacterClickListener {

    fun onItemClick(characterData: CharacterData)

}