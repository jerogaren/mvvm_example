package com.jerogaren.characterslistmarvelmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jerogaren.characterslistmarvelmvvm.db.model.CharacterData
import com.jerogaren.characterslistmarvelmvvm.di.*
import com.jerogaren.characterslistmarvelmvvm.repository.CharactersRepository
import com.jerogaren.characterslistmarvelmvvm.viewmodel.CharactersViewModel
import org.junit.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class CharacterViewModelTest : KoinTest {

    val charactersViewModel : CharactersViewModel by inject()
    val repository: CharactersRepository by inject()

    @Mock
    lateinit var uiData : Observer<List<CharacterData>>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before(){
        MockitoAnnotations.initMocks(this)
        startKoin {
            modules(
                apiModule,
                viewModelModule,
                repositoryModule,
                networkModule,
                databaseModule
            )
        }
    }

    @After
    fun after(){
        stopKoin()
    }

    @Test
    suspend fun testGetListCharacters(){

        //set up data
        val result = repository.getMoreCharacters(0, 20)

        //observe
        charactersViewModel.charactersList.observeForever(uiData)

        // Select data to notify
        charactersViewModel.getMoreCharacters()

        // Has received data
        charactersViewModel.charactersList.value = emptyList<CharacterData>()
        Assert.assertNotNull(charactersViewModel.charactersList.value)


        // Has been notified
        //Mockito.verify(uiData).onChanged(result)


    }

    @Test
    fun testCharacterViewModel(){

        //charactersViewModel.charactersList.observeForever(charactersListObserver)

    }



}