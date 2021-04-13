package com.jerogaren.characterslistmarvelmvvm.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jerogaren.characterslistmarvelmvvm.databinding.FragmentCharactersDetailsBinding
import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData
import com.jerogaren.characterslistmarvelmvvm.util.TAG
import com.jerogaren.characterslistmarvelmvvm.util.load
import com.jerogaren.characterslistmarvelmvvm.viewmodel.CharacterDetailViewModel
import com.jerogaren.characterslistmarvelmvvm.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterDetailsFragment : Fragment() {

    private val characterDetailsViewModel by viewModel<CharacterDetailViewModel>()
    private val mainActivityViewModel: MainActivityViewModel by sharedViewModel()

    companion object{
        const val TAG = "CharacterDetailsFrag"
        private const val KEY_DATA_CHARACTER = "character_data"

        fun newInstance(data: CharacterData) = CharacterDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_DATA_CHARACTER, data)
            }
        }
    }

    private var characterData: CharacterData? = null
    private lateinit var binding: FragmentCharactersDetailsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        characterData = arguments?.getParcelable(KEY_DATA_CHARACTER)
        Log.d(TAG, "onAttach: "+characterData.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.characterDetail = characterData
        val path = characterData?.thumbnail?.path?.split("//")?.last()
        binding.ivThumbnailDetail.load("https://"+path+"."+characterData?.thumbnail?.extension, binding.root.context)

        characterData?.let {
            characterDetailsViewModel.getCharacterDetail(it.id)
        }

        characterDetailsViewModel.listCharacterDetail.observe(viewLifecycleOwner,{
            Log.d(TAG, "DETAIL CHARACTER: $it")
        })


    }

    override fun onResume() {
        super.onResume()
        mainActivityViewModel.toolbarTitle.value = "Details"
    }

}