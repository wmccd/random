package com.wmccd.core_datastore.internal

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.core_datastore.internal.favourites.FavouritesDataStore
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class FavouriteDataStoreTest {

    private val testContext: Context = ApplicationProvider.getApplicationContext()
    private val favouritesDataStore = FavouritesDataStore(testContext)

    @Before
    fun setup(){
        runBlocking {
            favouritesDataStore.clearFavourites()
        }
    }

    @Test
    fun clearFavourites_emptyString_correct() = runTest {

        //assemble
        val expected = ""
        var actual = ""

        //act
        launch {
            actual = favouritesDataStore.getFavourites.first()
        }
        advanceUntilIdle()

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun clearFavourites_addId_correct() = runTest {

        //assemble
        val expected = "12"
        var actual = ""

        //act
        runBlocking {
            favouritesDataStore.saveFavourites(expected)
        }

        launch {
            actual = favouritesDataStore.getFavourites.first()
        }
        advanceUntilIdle()

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun clearFavourites_updateIds_correct() = runTest {

        //assemble
        val expected = "12"
        var actual = ""

        //act
        runBlocking {
            favouritesDataStore.saveFavourites("not expected")
        }
        runBlocking {
            favouritesDataStore.saveFavourites(expected)
        }

        launch{
            actual = favouritesDataStore.getFavourites.first()
        }
        advanceUntilIdle()

        //assert
        assertEquals(expected, actual)
    }

}