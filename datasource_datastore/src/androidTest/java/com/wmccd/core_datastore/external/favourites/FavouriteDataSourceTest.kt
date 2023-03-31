package com.wmccd.core_datastore.external.favourites

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class FavouriteDataSourceTest {

    private val testContext: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setup()= runTest{
        val favouritesDataSource = FavouritesDataSource(testContext)
        favouritesDataSource.clearFavourites()
    }

    @Test
    fun favouritesStream_emptyString_size() = runTest {

        //assemble
        val favouritesDataSource = FavouritesDataSource(testContext)
        val expected = 0

        //act
        val actual = favouritesDataSource.getFavourites.first().size

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun favouritesStream_addOne_listSizeIsOne() = runTest {

        //assemble
        val favouritesDataSource = FavouritesDataSource(testContext)
        val expected = 1

        //act
        favouritesDataSource.toggleFavourite("12", true)
        val actual = favouritesDataSource.getFavourites.first().size

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun favouritesStream_addOne_listContentIsCorrect() = runTest {

        //assemble
        val favouritesDataSource = FavouritesDataSource(testContext)
        val expected = "12"

        //act
        favouritesDataSource.toggleFavourite(expected, true)
        val actual = favouritesDataSource.getFavourites.first()

        //assert
        assertEquals(expected, actual[0])
    }

    @Test
    fun favouritesStream_addOneToggleOnAndOff_listIsZero() = runTest {

        //assemble
        val favouritesDataSource = FavouritesDataSource(testContext)
        val expected = 0

        //act
        favouritesDataSource.toggleFavourite("12", true)
        favouritesDataSource.toggleFavourite("12", false)
        val actual = favouritesDataSource.getFavourites.first().size

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun favouritesStream_addTwo_listContentIsCorrect() = runTest {

        //assemble
        val favouritesDataSource = FavouritesDataSource(testContext)
        val expected = 2

        //act
        favouritesDataSource.toggleFavourite("12", true)
        favouritesDataSource.toggleFavourite("14", true)
        val actual = favouritesDataSource.getFavourites.first().size

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun favouritesStream_toggleSameOneOnTwice_listIsTwo() = runTest {

        //assemble
        val favouritesDataSource = FavouritesDataSource(testContext)
        val expected = 2

        //act
        favouritesDataSource.toggleFavourite("12", true)
        favouritesDataSource.toggleFavourite("12", true)
        val actual = favouritesDataSource.getFavourites.first().size

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun favouritesStream_toggleSameOneOnTwiceThenOff_listIsZero() = runTest {

        //assemble
        val favouritesDataSource = FavouritesDataSource(testContext)
        val expected = 0

        //act
        favouritesDataSource.toggleFavourite("12", true)
        favouritesDataSource.toggleFavourite("12", true)
        favouritesDataSource.toggleFavourite("12", false)
        val actual = favouritesDataSource.getFavourites.first().size

        //assert
        assertEquals(expected, actual)
    }

}