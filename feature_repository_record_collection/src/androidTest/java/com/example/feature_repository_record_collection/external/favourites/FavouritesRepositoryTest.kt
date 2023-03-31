package com.example.feature_repository_record_collection.external.favourites

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wmccd.core_datastore.external.favourites.iFavouritesDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class FavouritesRepositoryTest{

    //val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun favouritesStream_noFavourites_countIsZero()= runTest{

        //assemble
        val dataSource: iFavouritesDataSource = MockFavouritesDataSource()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val favouritesRepository = FavouritesRepository(
            dataSource = dataSource,
            dispatcher = dispatcher
        )
        val expected = 0

        //act
        val actual = favouritesRepository.favouritesStream.first().size

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun favouritesStream_oneFavourite_countIsOne()= runTest{

        //assemble
        val dataSource: iFavouritesDataSource = MockFavouritesDataSource()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val favouritesRepository = FavouritesRepository(
            dataSource = dataSource,
            dispatcher = dispatcher
        )
        val expected = 1

        //act
        favouritesRepository.toggleFavourite("1", true)
        val actual = favouritesRepository.favouritesStream.first().size

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun favouritesStream_toggleOnAndOff_countIsZero()= runTest{

        //assemble
        val dataSource: iFavouritesDataSource = MockFavouritesDataSource()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val favouritesRepository = FavouritesRepository(
            dataSource = dataSource,
            dispatcher = dispatcher
        )
        val expected = 0

        //act
        favouritesRepository.toggleFavourite("1", true)
        favouritesRepository.toggleFavourite("1", false)

        val actual = favouritesRepository.favouritesStream.first().size

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun favouritesStream_toggleTwoOn_countIsTwo()= runTest{

        //assemble
        val dataSource: iFavouritesDataSource = MockFavouritesDataSource()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val favouritesRepository = FavouritesRepository(
            dataSource = dataSource,
            dispatcher = dispatcher
        )
        val expected = 2

        //act
        favouritesRepository.toggleFavourite("1", true)
        favouritesRepository.toggleFavourite("2", true)

        val actual = favouritesRepository.favouritesStream.first().size

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun favouritesStream_toggleFavouriteOnTwice_countIsTwo()= runTest{

        //assemble
        val dataSource: iFavouritesDataSource = MockFavouritesDataSource()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val favouritesRepository = FavouritesRepository(
            dataSource = dataSource,
            dispatcher = dispatcher
        )
        val expected = 2

        //act
        favouritesRepository.toggleFavourite("1", true)
        favouritesRepository.toggleFavourite("1", true)

        val actual = favouritesRepository.favouritesStream.first().size

        //assert
        Assert.assertEquals(expected, actual)
    }
}
