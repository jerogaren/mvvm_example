package com.jerogaren.characterslistmarvelmvvm.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jerogaren.characterslistmarvelmvvm.R
import com.jerogaren.characterslistmarvelmvvm.databinding.ItemCharacterBinding
import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData

class CharactersAdapter(val context: Context?, val clickListener: CharacterClickListener) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    var charactersList = listOf<CharacterData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val viewBinding: ItemCharacterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_character, parent, false
        )
        return CharacterViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    fun setCharacters(characters: List<CharacterData>){
        this.charactersList = characters
        notifyDataSetChanged()
    }


    inner class CharacterViewHolder(private val viewBinding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            val row = charactersList[position]
            viewBinding.characters = row
            viewBinding.characterClickInterface = clickListener
        }
    }

}
