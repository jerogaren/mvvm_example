package com.jerogaren.characterslistmarvelmvvm.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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
        charactersViewModel.getAllCharacters()
        charactersViewModel.charactersList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "CHARACTERS: "+it.size.toString())
            if (it.isNotEmpty() && it != null){
                charactersAdapter.setCharacters(it)
            }
        })

    }

    private fun setView() {
        charactersAdapter = CharactersAdapter(context, this)
        binding.rvCountries.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvCountries.adapter = charactersAdapter
        binding.rvCountries.isNestedScrollingEnabled = false
    }

    override fun onItemClick(characterData: CharacterData) {
        Log.d(TAG, "clicked: " +characterData.name)
    }

}