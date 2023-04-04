package com.example.feature_repository_record_collection.internal

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.core_database.external.albums.models.AlbumDataSourceModel
import com.example.feature_repository_record_collection.external.albums.models.AlbumsRepositoryModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class AlbumDataSourceAndRepositoryModelConverterTest {

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun convert_dataSourceToRepositoryModel_correctContent() {

        //assemble
        val converter = AlbumDataSourceAndRepositoryModelConverter()
        val albumDataSourceModel = AlbumDataSourceModel(
            albumId = 1,
            albumArtist = "The Mighty Bobbins",
            albumName = "Bobbins Bounce"
        )

        //act
        val actual = converter.convert(albumDataSourceModel)

        //assert
        assertEquals(albumDataSourceModel.albumId, actual.id)
        assertEquals(albumDataSourceModel.albumArtist, actual.artist)
        assertEquals(albumDataSourceModel.albumName, actual.name )
    }

    @Test
    fun convert_repositoryModelToDataSourceModel_correctContent() {

        //assemble
        val converter = AlbumDataSourceAndRepositoryModelConverter()
        val albumsRepositoryModel = AlbumsRepositoryModel(
            id = 1,
            artist = "The Mighty Bobbins",
            name = "Bobbins Bounce"
        )

        //act
        val actual = converter.convert(albumsRepositoryModel)

        //assert
        assertEquals(albumsRepositoryModel.id, actual.albumId)
        assertEquals(albumsRepositoryModel.artist, actual.albumArtist)
        assertEquals(albumsRepositoryModel.name, actual.albumName )
    }

}