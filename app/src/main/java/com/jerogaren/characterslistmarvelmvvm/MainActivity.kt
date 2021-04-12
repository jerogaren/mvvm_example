package com.jerogaren.characterslistmarvelmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.jerogaren.characterslistmarvelmvvm.util.replaceFragment
import com.jerogaren.characterslistmarvelmvvm.view.CharactersFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
        addCharactersFragment()
    }


    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar?.title = "All Characters"
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
        } else {
            super.onBackPressed()
        }
    }
}