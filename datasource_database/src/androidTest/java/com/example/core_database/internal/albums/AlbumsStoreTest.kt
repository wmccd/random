package com.example.core_database.internal.albums

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.core_database.internal.AlbumDatabase
import com.example.core_database.internal.albums.AlbumEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.coroutines.test.runTest
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class AlbumsStoreTest {

    // Responsibility
    // test the SQL queries

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val albumsStore = AlbumDatabase.instance(context = appContext).albumsStore()

    @Before
    fun setup() = runTest{
        albumsStore.deleteAll()
    }

    @Test
    fun insert_oneAdded_countIsOne()= runTest{

        //assemble
        val expected = 1
        var actual = 0

        //act
        albumsStore.insert(albumEntity = AlbumEntity())
        actual = albumsStore.fetchAlbumCount().first()

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun insert_twoAdded_countIsTwo()= runTest{

        //assemble
        val expected = 2
        var actual = 0

        //act
        albumsStore.insert(albumEntity = AlbumEntity())
        albumsStore.insert(albumEntity = AlbumEntity())
        actual = albumsStore.fetchAlbumCount().first()

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun insert_oneAdded_contentCorrect()= runTest{

        //assemble
        val expectedId = 1L
        val expectedArtist = "The Mighty Bobbins"
        val expectedAlbum = "Bobbins is Back!!"
        val expectedEntity = AlbumEntity(
            albumId = expectedId,
            albumArtist = expectedArtist,
            albumName = expectedAlbum
        )
        var actual = listOf<AlbumEntity>()

        //act
        albumsStore.insert(albumEntity = expectedEntity)
        actual = albumsStore.fetchAll().first()

        //assert
        Assert.assertEquals(expectedId, actual[0].albumId)
        Assert.assertEquals(expectedArtist, actual[0].albumArtist)
        Assert.assertEquals(expectedAlbum, actual[0].albumName)
    }

    @Test
    fun insert_twoAdded_contentCorrect()= runTest{

        //assemble
        val expectedEntity1 = AlbumEntity(
            albumId = 1L,
            albumArtist = "The Mighty Bobbins",
            albumName = "Bobbins is Back!!"
        )

        val expectedEntity2 = AlbumEntity(
            albumId = 2L,
            albumArtist = "The Mighty Bobbins2",
            albumName = "Bobbins is Back2!!"
        )
        var actual = listOf<AlbumEntity>()

        //act
        albumsStore.insert(albumEntity = expectedEntity1)
        albumsStore.insert(albumEntity = expectedEntity2)

        actual = albumsStore.fetchAll().first()

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
        albumsStore.insert(albumEntity = AlbumEntity(
            albumId = 1L,
            albumArtist = "Bobbins",
            albumName = "Back!!"
        ))

        actual = albumsStore.fetchAllArtists().first().size

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun fetchAllArtistsStream_twoAddedSameArtist_countIsOne()= runTest{

        //assemble
        val expected = 1
        var actual = 0

        //act
        albumsStore.insert(albumEntity = AlbumEntity(
            albumId = 1L,
            albumArtist = "Bobbins",
            albumName = "Back!!"
        ))

        albumsStore.insert(albumEntity = AlbumEntity(
            albumId = 2L,
            albumArtist = "Bobbins",
            albumName = "Back Again!!"
        ))

        actual = albumsStore.fetchAllArtists().first().size

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun fetchAllArtistsStream_twoAddedDifferentArtists_countIsTwo()= runTest{

        //assemble
        val expected = 2
        var actual = 0

        //act
        albumsStore.insert(albumEntity = AlbumEntity(
            albumId = 1L,
            albumArtist = "Bobbins",
            albumName = "Back!!"
        ))

        albumsStore.insert(albumEntity = AlbumEntity(
            albumId = 2L,
            albumArtist = "Not the Bobbins",
            albumName = "Back Again!!"
        ))

        actual = albumsStore.fetchAllArtists().first().size

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun update_insertThenUpdate_update()= runTest{

        //assemble
        val originalAlbumName = "Bobbing Bobbins"
        val updatedAlbumName = "The Bobbing Bobbins Bop"
        val originalEntity = AlbumEntity(
            albumId = 1L,
            albumArtist = "Bobbins",
            albumName = originalAlbumName
        )

        //act
        albumsStore.insert(albumEntity = originalEntity)
        albumsStore.update(albumEntity = originalEntity.copy(
            albumName = updatedAlbumName
        ))

        val actual = albumsStore.fetchAll().first()

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
        albumsStore.insert(albumEntity = AlbumEntity(
            albumId = 1
        ))
        albumsStore.insert(albumEntity = AlbumEntity(
            albumId = 2
        ))
        actualDeleteCount = albumsStore.deleteByAlbumId(2)
        actualAlbumCount = albumsStore.fetchAlbumCount().first()

        //assert
        Assert.assertEquals(expectedDeleteCount, actualDeleteCount)
        Assert.assertEquals(expectedAlbumCount, actualAlbumCount)

    }

}
