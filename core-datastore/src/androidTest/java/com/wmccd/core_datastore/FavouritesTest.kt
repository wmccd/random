package com.wmccd.core_datastore

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.core_datastore.store.Favourites
import com.wmccd.core_datastore.store.FavouritesDataStore
import com.wmccd.core_datastore.store.iFavouritesDataStore
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class FavouritesTest {

    private val testContext: Context = ApplicationProvider.getApplicationContext()

    private val favouritesDataStore: iFavouritesDataStore = TestFavouritesDataStore()

    @Before
    fun setup(){
        runBlocking {
            favouritesDataStore.clearFavourites()
        }
    }

    @Test
    fun favouritesStream_empty_emptyList() = runTest {

        //assemble
        val favourites = Favourites( dataStore = favouritesDataStore)
        val expectedSize = 0
        var actual = listOf<String>()

        //act
        launch {
            actual = favourites.favouritesStream.first()
            val x = 1
        }
        advanceUntilIdle()

        //assert
        assertEquals(expectedSize, actual.size)

    }

    @Test
    fun favouritesStream_oneFavouriteAdded_oneItemInList() = runTest{

        //assemble
        val favourites = Favourites( dataStore = favouritesDataStore)
        val expectedSize = 1
        val idToAdd = "12"
        var actual = listOf<String>()

        //act
        favourites.toggleFavourite(idToAdd, true)
        actual = favourites.favouritesStream.first()

        //assert
        assertEquals(expectedSize, actual.size)
    }

    @Test
    fun favouritesStream_twoFavouritesAdded_twoItemsInList() = runTest{

        //assemble
        val favourites = Favourites( dataStore = favouritesDataStore)
        val expectedSize = 2
        val idToAdd1 = "12"
        val idToAdd2 = "37"
        var actual = listOf<String>()

        //act
        favourites.toggleFavourite(idToAdd1, true)
        favourites.toggleFavourite(idToAdd2, true)
        actual = favourites.favouritesStream.first()

        //assert
        assertEquals("actual was <$actual>", expectedSize, actual.size)
    }

    @Test
    fun favouritesStream_threeFavouritesAddedOneRemoved_twoItemsInList() = runTest{

        //assemble
        val favourites = Favourites( dataStore = favouritesDataStore)
        val expectedSize = 2
        val idToAdd1 = "12"
        val idToAdd2 = "37"
        val idToAdd3 = "54"
        var actual = listOf<String>()

        //act
        favourites.toggleFavourite(idToAdd1, true)
        favourites.toggleFavourite(idToAdd2, true)
        favourites.toggleFavourite(idToAdd3, true)
        favourites.toggleFavourite(idToAdd1, false)
        actual = favourites.favouritesStream.first()

        //assert
        assertEquals("actual was <$actual>", expectedSize, actual.size)
    }

}