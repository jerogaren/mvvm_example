package com.jerogaren.characterslistmarvelmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.jerogaren.characterslistmarvelmvvm.util.replaceFragment
import com.jerogaren.characterslistmarvelmvvm.view.CharactersFragment
import com.jerogaren.characterslistmarvelmvvm.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "MainActivity"
    }

    private val mainActivityViewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addCharactersFragment()

        mainActivityViewModel.toolbarTitle.observe(this, {toolbarTitle ->
            Log.d(TAG, "mainActivityViewModel: "+toolbarTitle )
            findViewById<Toolbar>(R.id.toolbar).title = toolbarTitle
        })
    }

    private fun addCharactersFragment() {

        replaceFragment(
            CharactersFragment(),
            R.id.fragment_container
        )

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            mainActivityViewModel.toolbarTitle.value = "All Characters"
        } else {
            super.onBackPressed()
        }
    }
}