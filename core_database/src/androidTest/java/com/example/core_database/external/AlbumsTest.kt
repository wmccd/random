package com.example.core_database.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.core_database.external.models.AlbumModel
import com.example.core_database.internal.albums.MockAlbumsDataSource
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.coroutines.test.runTest



@RunWith(AndroidJUnit4::class)
class AlbumsTest {


    @Test
    fun fetchAllStream_mocked_correctCount()= runTest{

        //assemble
        val dataSource: iAlbumsDataSource = MockAlbumsDataSource()
        val expected = 3
        var actual = 0

        //act
        launch {
            dataSource.fetchAllStream.collect{
                actual = it.size
            }
        }
        advanceUntilIdle()

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun fetchAllStream_mocked_correctContent()= runTest{

        //assemble
        val dataSource: iAlbumsDataSource = MockAlbumsDataSource()
        var actual = listOf<AlbumModel>()

        //act
        launch {
            dataSource.fetchAllStream.collect{
                actual = it
            }
        }
        advanceUntilIdle()

        //assert
        Assert.assertEquals(1, actual[0].albumId)
        Assert.assertEquals(2, actual[1].albumId)
        Assert.assertEquals(3, actual[2].albumId)
    }

    @Test
    fun fetchAllArtistsStream_mocked_correctCount()= runTest{

        //assemble
        val dataSource: iAlbumsDataSource = MockAlbumsDataSource()
        val expected = 3
        var actual = 0

        //act
        launch {
            dataSource.fetchAllArtistsStream.collect{
                actual = it.size
            }
        }
        advanceUntilIdle()

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun fetchAllArtistsStream_mocked_correctContent()= runTest{

        //assemble
        val dataSource: iAlbumsDataSource = MockAlbumsDataSource()
        var actual = listOf<String>()

        //act
        launch {
            dataSource.fetchAllArtistsStream.collect{
                actual = it
            }
        }
        advanceUntilIdle()

        //assert
        Assert.assertEquals("Bobbins", actual[0])
        Assert.assertEquals("The Mighty Bobbins", actual[1])
        Assert.assertEquals("Bob Bobbins", actual[2])
    }

    @Test
    fun fetchAlbumCountStream_mocked_correctCount()= runTest{

        //assemble
        val dataSource: iAlbumsDataSource = MockAlbumsDataSource()
        val expected = 99
        var actual = 0

        //act
        launch {
            dataSource.fetchAlbumCountStream.collect{
                actual = it
            }
        }
        advanceUntilIdle()

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun insert_mocked_lastInvokedCorrect()= runTest{

        //assemble
        val dataSource = MockAlbumsDataSource()
        val albums = Albums(dataSource = dataSource)
        val expected = "insert"
        var actual = ""

        //act
        albums.insert(albumModel = AlbumModel() )
        actual = dataSource.lastInvoke

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun update_mocked_correct()= runTest{

        //assemble
        val dataSource: iAlbumsDataSource = MockAlbumsDataSource()
        val albums = Albums(dataSource = dataSource)
        val expected = 4

        //act
        val actual = albums.update(
            albumModel = AlbumModel()
        )

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun deleteByAlbumId_mocked_correct()= runTest{

        //assemble
        val dataSource: iAlbumsDataSource = MockAlbumsDataSource()
        val albums = Albums(dataSource = dataSource)
        val expected = 5

        //act
        val actual = albums.deleteByAlbumId(albumId = 1)

        //assert
        Assert.assertEquals(expected, actual)
    }


    @Test
    fun deleteAll_mocked_lastInvokedCorrect()= runTest{

        //assemble
        val dataSource = MockAlbumsDataSource()
        val albums = Albums(dataSource = dataSource)
        val expected = "deleteAll"
        var actual = ""

        //act
        albums.deleteAll()
        actual = dataSource.lastInvoke

        //assert
        Assert.assertEquals(expected, actual)
    }



    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.core_database.test", appContext.packageName)
    }
}
