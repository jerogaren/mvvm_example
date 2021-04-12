package com.jerogaren.characterslistmarvelmvvm.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.AbsListViewBindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jerogaren.characterslistmarvelmvvm.R
import com.jerogaren.characterslistmarvelmvvm.databinding.FragmentCharactersBinding
import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData
import com.jerogaren.characterslistmarvelmvvm.util.TAG
import com.jerogaren.characterslistmarvelmvvm.viewmodel.CharactersViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment(), CharacterClickListener {


    private val charactersViewModel by viewModel<CharactersViewModel>()
    private lateinit var binding: FragmentCharactersBinding
    private lateinit var charactersAdapter : CharactersAdapter
    private var isLoading = true

    companion object{
        const val FTAG = "CharactersFragment"
        const val NEXT_CALL_OFFSET = 3
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = DataBindingUtil.inflate(inflater,
            R.layout.fragment_characters, container, false)
        val mRootView = binding.root
        binding.lifecycleOwner = this
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        binding.viewModel = charactersViewModel
        charactersViewModel.getMoreCharacters()
        charactersViewModel.charactersList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "CHARACTERS: "+it.size.toString())
            if (it.isNotEmpty() && it != null){
                isLoading = false
                charactersAdapter.addCharacters(it)
            }
        })

    }

    override fun onItemClick(characterData: CharacterData) {
        Log.d(TAG, "clicked: " +characterData.name)
    }

    private fun setView() {
        charactersAdapter = CharactersAdapter(context, this)
        binding.rvCharacters.setHasFixedSize(true)
        binding.rvCharacters.adapter = charactersAdapter
        //binding.rvCharacters.isNestedScrollingEnabled = false
        //binding.rvCharacters.layoutManager = GridLayoutManager(context, 2)
        (binding.rvCharacters.adapter as CharactersAdapter).stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                Log.d(FTAG, "onScrollStateChanged LAST ITEM POSITION: " +(binding.rvCharacters.layoutManager as LinearLayoutManager).findLastVisibleItemPosition())
                val lastItemPosition = (binding.rvCharacters.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val diffLastVisibleAndTotalSize = (binding.rvCharacters.adapter as CharactersAdapter).itemCount - lastItemPosition
                Log.d(FTAG, "DIFF LAST ITEM POSITION: $diffLastVisibleAndTotalSize")
                if (diffLastVisibleAndTotalSize <= NEXT_CALL_OFFSET && !isLoading){
                    isLoading = true
                    charactersViewModel.getMoreCharacters()
                }
            }
        })

    }



}