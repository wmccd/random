package com.example.feature_viewmodel_record_collection.external

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.feature_viewmodel_record_collection.MockAlbumsRepository
import com.example.feature_viewmodel_record_collection.MockFavouritesRepository
import com.example.feature_viewmodel_record_collection.external.models.RecordVMModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RecordCollectionViewModelTest {

    @Test
    fun combinedData_noItems_emptyList() = runTest{
        //assemble
        val albumsRepository = MockAlbumsRepository()
        val favouritesRepository = MockFavouritesRepository()
        val recordCollectionViewModel = RecordCollectionViewModel(
            albumsRepository = albumsRepository,
            favouritesRepository = favouritesRepository
        )
        val expectedSize = 0

        //act
        val actual = recordCollectionViewModel.combinedData.first().size

        //assert
        assertEquals(expectedSize, actual)
    }

    @Test
    fun combinedData_oneAlbumAdded_oneItemInList() = runTest{

        //assemble
        val albumsRepository = MockAlbumsRepository()
        val favouritesRepository = MockFavouritesRepository()
        val recordVMModel = RecordVMModel(
            id = 1,
            artist = "Bobbins",
            name = "Bobbins at 33 1/3"
        )
        val recordCollectionViewModel = RecordCollectionViewModel(
            albumsRepository = albumsRepository,
            favouritesRepository = favouritesRepository
        )
        val expectedSize = 1

        //act
        recordCollectionViewModel.addAlbum(recordVMModel)
        val actual = recordCollectionViewModel.combinedData.first().size

        //assert
        assertEquals(expectedSize, actual)
    }

    @Test
    fun combinedData_oneAlbumAdded_contentCorrect() = runTest{
        //assemble
        val albumsRepository = MockAlbumsRepository()
        val favouritesRepository = MockFavouritesRepository()
        val recordVMModel = RecordVMModel(
            id = 1,
            artist = "Bobbins",
            name = "Bobbins at 33 1/3"
        )

        val recordCollectionViewModel = RecordCollectionViewModel(
            albumsRepository = albumsRepository,
            favouritesRepository = favouritesRepository
        )
        recordCollectionViewModel.addAlbum(recordVMModel = recordVMModel)
        recordCollectionViewModel.toggleFavourite(
            id  = recordVMModel.id.toString(),
            favourite = true
        )

        //act
        val actual = recordCollectionViewModel.combinedData.first()

        //assert
        assertEquals(recordVMModel.id, actual[0].recordVMModel.id)
        assertEquals(recordVMModel.artist, actual[0].recordVMModel.artist)
        assertEquals(recordVMModel.name, actual[0].recordVMModel.name)
        assertEquals(true, actual[0].favourite)
    }

    @Test
    fun combinedData_oneAlbumAddedThenDelete_listIsEmpty() = runTest{
        //assemble
        val albumsRepository = MockAlbumsRepository()
        val favouritesRepository = MockFavouritesRepository()
        val recordVMModel = RecordVMModel(
            id = 1,
            artist = "Bobbins",
            name = "Bobbins at 33 1/3"
        )
        val recordCollectionViewModel = RecordCollectionViewModel(
            albumsRepository = albumsRepository,
            favouritesRepository = favouritesRepository
        )
        val expected = 0
        recordCollectionViewModel.addAlbum(recordVMModel = recordVMModel)
        recordCollectionViewModel.deleteAlbum(id = recordVMModel.id.toInt())

        //act
        val actual = recordCollectionViewModel.combinedData.first().size

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun combinedData_twoAlbumsOneFavourite_contentIsCorrect() = runTest{
        //assemble
        val albumsRepository = MockAlbumsRepository()
        val favouritesRepository = MockFavouritesRepository()
        val recordVMModel1 = RecordVMModel(
            id = 1,
            artist = "Bobbins",
            name = "Bobbins at 33 1/3"
        )
        val recordVMModel2 = RecordVMModel(
            id = 2,
            artist = "The Mighty Bobbins",
            name = "Best of Bobbins"
        )
        val recordCollectionViewModel = RecordCollectionViewModel(
            albumsRepository = albumsRepository,
            favouritesRepository = favouritesRepository
        )
        recordCollectionViewModel.addAlbum(recordVMModel = recordVMModel1)
        recordCollectionViewModel.addAlbum(recordVMModel = recordVMModel2)
        recordCollectionViewModel.toggleFavourite(
            id = recordVMModel2.id.toString(),
            favourite = true
        )

        //act
        val actual = recordCollectionViewModel.combinedData.first()

        //assert
        assertEquals(recordVMModel1.id, actual[0].recordVMModel.id)
        assertEquals(recordVMModel1.artist, actual[0].recordVMModel.artist)
        assertEquals(recordVMModel1.name, actual[0].recordVMModel.name)
        assertEquals(recordVMModel2.id, actual[1].recordVMModel.id)
        assertEquals(recordVMModel2.artist, actual[1].recordVMModel.artist)
        assertEquals(recordVMModel2.name, actual[1].recordVMModel.name)
    }




    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.feature_viewmodel_record_collection.test", appContext.packageName)
    }
}