package com.example.feature_repository_record_collection.external.albums

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.core_database.external.albums.iAlbumsDataSource
import com.example.feature_repository_record_collection.external.albums.models.AlbumsRepositoryModel
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
class AlbumsRepositoryTest{

    //val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun fetchAllStream_mocked_correctCount()= runTest{

        //assemble
        val dataSource: iAlbumsDataSource = MockAlbumsDataSource()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val albumsRepository = AlbumsRepository(
            dataSource = dataSource,
            dispatcher = dispatcher
        )

        val expected = 3
        var actual = 0

        //act
        actual = albumsRepository.allAlbums.first().size

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun fetchAllStream_mocked_correctContent()= runTest{

        //assemble
        val dataSource: iAlbumsDataSource = MockAlbumsDataSource()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val albumsRepository = AlbumsRepository(
            dataSource = dataSource,
            dispatcher = dispatcher
        )

        //act
        val actual = albumsRepository.allAlbums.first()

        //assert
        Assert.assertEquals(1, actual[0].id)
        Assert.assertEquals(2, actual[1].id)
        Assert.assertEquals(3, actual[2].id)
    }

    @Test
    fun fetchAllArtistsStream_mocked_correctCount()= runTest{

        //assemble
        val dataSource: iAlbumsDataSource = MockAlbumsDataSource()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val albumsRepository = AlbumsRepository(
            dataSource = dataSource,
            dispatcher = dispatcher
        )
        val expected = 3

        //act
        val actual = albumsRepository.allArtists.first().size


        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun fetchAllArtistsStream_mocked_correctContent()= runTest{

        //assemble
        val dataSource: iAlbumsDataSource = MockAlbumsDataSource()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val albumsRepository = AlbumsRepository(
            dataSource = dataSource,
            dispatcher = dispatcher
        )

        //act
        val actual = albumsRepository.allArtists.first()

        //assert
        Assert.assertEquals("Bobbins", actual[0])
        Assert.assertEquals("The Mighty Bobbins", actual[1])
        Assert.assertEquals("Bob Bobbins", actual[2])
    }

    @Test
    fun fetchAlbumCountStream_mocked_correctCount()= runTest{

        //assemble
        val dataSource: iAlbumsDataSource = MockAlbumsDataSource()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val albumsRepository = AlbumsRepository(
            dataSource = dataSource,
            dispatcher = dispatcher
        )
        val expected = 99

        //act
        val actual = albumsRepository.albumCount.first()

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun insert_mocked_lastInvokedCorrect()= runTest{

        //assemble
        val dataSource = MockAlbumsDataSource()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val albumsRepository = AlbumsRepository(
            dataSource = dataSource,
            dispatcher = dispatcher
        )
        val expected = "insert"
        var actual = ""

        //act
        albumsRepository.insertAlbum(
            albumsRepositoryModel = AlbumsRepositoryModel()
        )
        actual = dataSource.lastInvoke

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun update_mocked_correct()= runTest{

        //assemble
        val dataSource = MockAlbumsDataSource()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val albumsRepository = AlbumsRepository(
            dataSource = dataSource,
            dispatcher = dispatcher
        )
        val expected = 4

        //act
        val actual = albumsRepository.updateAlbum(
            albumsRepositoryModel = AlbumsRepositoryModel()
        )

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun deleteByAlbumId_mocked_correct()= runTest{

        //assemble
        val dataSource = MockAlbumsDataSource()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val albumsRepository = AlbumsRepository(
            dataSource = dataSource,
            dispatcher = dispatcher
        )
        val expected = 5

        //act
        val actual = albumsRepository.deleteAlbum(albumId = 1)

        //assert
        Assert.assertEquals(expected, actual)
    }


    @Test
    fun deleteAll_mocked_lastInvokedCorrect()= runTest{

        //assemble
        val dataSource = MockAlbumsDataSource()
        val dispatcher = StandardTestDispatcher(testScheduler)
        val albumsRepository = AlbumsRepository(
            dataSource = dataSource,
            dispatcher = dispatcher
        )
        val expected = "deleteAll"
        var actual = ""

        //act
        albumsRepository.deleteAllAlbums()
        actual = dataSource.lastInvoke

        //assert
        Assert.assertEquals(expected, actual)
    }

}
