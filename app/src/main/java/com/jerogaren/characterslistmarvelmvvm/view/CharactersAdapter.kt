package com.jerogaren.characterslistmarvelmvvm.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jerogaren.characterslistmarvelmvvm.R
import com.jerogaren.characterslistmarvelmvvm.databinding.ItemCharacterBinding
import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData
import com.jerogaren.characterslistmarvelmvvm.util.load

class CharactersAdapter(val context: Context?, val clickListener: CharacterClickListener) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    var charactersList = mutableListOf<CharacterData>()


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
        this.charactersList = characters.toMutableList()
        notifyDataSetChanged()
    }

    fun addCharacters(characters: List<CharacterData>){
        this.charactersList.addAll(characters)
        notifyDataSetChanged()
    }


    inner class CharacterViewHolder(private val viewBinding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            val row = charactersList[position]
            viewBinding.characters = row
            //Glide.with(viewBinding.root.context).load(row.thumbnail?.path+"."+row.thumbnail?.extension).into(viewBinding.imgThumbnail)
            val path = row.thumbnail?.path?.split("//")?.last()

            viewBinding.imgThumbnail.load("https://"+path+"."+row.thumbnail?.extension, viewBinding.root.context)
            viewBinding.characterClickInterface = clickListener
        }
    }

}
