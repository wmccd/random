package com.example.core_database.external

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.core_database.external.albums.AlbumsDataSource
import com.example.core_database.external.albums.models.AlbumDataSourceModel
import kotlinx.coroutines.flow.first
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.coroutines.test.runTest
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class AlbumsDataSourceTest {

    // Responsibility
    // test the transformations of flows of entities to/from models
    // there should be no reference to a database entity here

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val albumsDataSource = AlbumsDataSource(context = appContext)

    @Before
    fun setup() = runTest{
        albumsDataSource.deleteAll()
    }

    @Test
    fun insert_oneAdded_countIsOne()= runTest{

        //assemble
        val expected = 1
        var actual = 0

        //act
        albumsDataSource.insert(albumDataSourceModel = AlbumDataSourceModel())
        actual = albumsDataSource.fetchAlbumCountStream.first()

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun insert_twoAdded_countIsTwo()= runTest{

        //assemble
        val expected = 2
        var actual = 0

        //act
        albumsDataSource.insert(albumDataSourceModel = AlbumDataSourceModel())
        albumsDataSource.insert(albumDataSourceModel = AlbumDataSourceModel())
        actual = albumsDataSource.fetchAlbumCountStream.first()

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun insert_oneAdded_contentCorrect()= runTest{

        //assemble
        val expectedId = 1L
        val expectedArtist = "The Mighty Bobbins"
        val expectedAlbum = "Bobbins is Back!!"
        val expectedModel = AlbumDataSourceModel(
            albumId = expectedId,
            albumArtist = expectedArtist,
            albumName = expectedAlbum
        )
        var actual = listOf<AlbumDataSourceModel>()

        //act
        albumsDataSource.insert(albumDataSourceModel = expectedModel)
        actual = albumsDataSource.fetchAllStream.first()

        //assert
        Assert.assertEquals(expectedId, actual[0].albumId)
        Assert.assertEquals(expectedArtist, actual[0].albumArtist)
        Assert.assertEquals(expectedAlbum, actual[0].albumName)
    }

    @Test
    fun insert_twoAdded_contentCorrect()= runTest{

        //assemble
        val expectedEntity1 = AlbumDataSourceModel(
            albumId = 1L,
            albumArtist = "The Mighty Bobbins",
            albumName = "Bobbins is Back!!"
        )

        val expectedEntity2 = AlbumDataSourceModel(
            albumId = 2L,
            albumArtist = "The Mighty Bobbins2",
            albumName = "Bobbins is Back2!!"
        )
        var actual = listOf<AlbumDataSourceModel>()

        //act
        albumsDataSource.insert(albumDataSourceModel = expectedEntity1)
        albumsDataSource.insert(albumDataSourceModel = expectedEntity2)

        actual = albumsDataSource.fetchAllStream.first()

        //assert
        Assert.assertEquals("Test 1",expectedEntity1.albumId, actual[0].albumId)
        Assert.assertEquals("Test 2", expectedEntity1.albumArtist, actual[0].albumArtist)
        Assert.assertEquals("Test 3", expectedEntity1.albumName, actual[0].albumName)

        Assert.assertEquals("Test 4", expectedEntity2.albumId, actual[1].albumId)
        Assert.assertEquals("Test 5", expectedEntity2.albumArtist, actual[1].albumArtist)
        Assert.assertEquals("Test 6", expectedEntity2.albumName, actual[1].albumName)
    }

    @Test
    fun fetchAllArtistsStream_oneAdded_countIsOne()= runTest{

        //assemble
        val expected = 1
        var actual = 0

        //act
        albumsDataSource.insert(albumDataSourceModel = AlbumDataSourceModel(
            albumId = 1L,
            albumArtist = "Bobbins",
            albumName = "Back!!"
        ))

        actual = albumsDataSource.fetchAllArtistsStream.first().size

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun fetchAllArtistsStream_twoAddedSameArtist_countIsOne()= runTest{

        //assemble
        val expected = 1
        var actual = 0

        //act
        albumsDataSource.insert(albumDataSourceModel = AlbumDataSourceModel(
            albumId = 1L,
            albumArtist = "Bobbins",
            albumName = "Back!!"
        ))

        albumsDataSource.insert(albumDataSourceModel = AlbumDataSourceModel(
            albumId = 2L,
            albumArtist = "Bobbins",
            albumName = "Back Again!!"
        ))

        actual = albumsDataSource.fetchAllArtistsStream.first().size

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun fetchAllArtistsStream_twoAddedDifferentArtists_countIsTwo()= runTest{

        //assemble
        val expected = 2
        var actual = 0

        //act
        albumsDataSource.insert(albumDataSourceModel = AlbumDataSourceModel(
            albumId = 1L,
            albumArtist = "Bobbins",
            albumName = "Back!!"
        ))

        albumsDataSource.insert(albumDataSourceModel = AlbumDataSourceModel(
            albumId = 2L,
            albumArtist = "Not the Bobbins",
            albumName = "Back Again!!"
        ))

        actual = albumsDataSource.fetchAllArtistsStream.first().size

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun update_insertThenUpdate_update()= runTest{

        //assemble
        val originalAlbumName = "Bobbing Bobbins"
        val updatedAlbumName = "The Bobbing Bobbins Bop"
        val originalModel = AlbumDataSourceModel(
            albumId = 1L,
            albumArtist = "Bobbins",
            albumName = originalAlbumName
        )

        //act
        albumsDataSource.insert(albumDataSourceModel = originalModel)
        albumsDataSource.update(albumDataSourceModel = originalModel.copy(
            albumName = updatedAlbumName
        ))

        val actual = albumsDataSource.fetchAllStream.first()

        //assert
        Assert.assertEquals(updatedAlbumName, actual[0].albumName)
    }

    @Test
    fun deleteByAlbumId_twoAddedOneDeleted_countIsOne()= runTest{

        //assemble
        val expectedDeleteCount = 1
        var actualDeleteCount = 0

        val expectedAlbumCount = 1
        var actualAlbumCount = 0

        //act
        albumsDataSource.insert(albumDataSourceModel = AlbumDataSourceModel(
            albumId = 1
        ))
        albumsDataSource.insert(albumDataSourceModel = AlbumDataSourceModel(
            albumId = 2
        ))
        actualDeleteCount = albumsDataSource.deleteByAlbumId(2)
        actualAlbumCount = albumsDataSource.fetchAlbumCountStream.first()

        //assert
        Assert.assertEquals(expectedDeleteCount, actualDeleteCount)
        Assert.assertEquals(expectedAlbumCount, actualAlbumCount)

    }

}
