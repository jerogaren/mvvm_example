package com.jerogaren.characterslistmarvelmvvm.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jerogaren.characterslistmarvelmvvm.R
import com.jerogaren.characterslistmarvelmvvm.databinding.ItemDetailBinding

class CharactersDetailsAdapter(val context: Context?) :
    RecyclerView.Adapter<CharactersDetailsAdapter.CharacterDetailViewHolder>() {

    var itemsList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterDetailViewHolder {
        val viewBinding: ItemDetailBinding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_detail, parent, false
        )
        return CharacterDetailViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: CharacterDetailViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun setItems(items: List<String>){
        this.itemsList = items.toMutableList()
        notifyDataSetChanged()
    }

    inner class CharacterDetailViewHolder(private val viewBinding: ItemDetailBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            viewBinding.tvItemDetail.text = itemsList[position]
        }
    }

}
