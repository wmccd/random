package com.example.feature_viewmodel_record_collection.internal

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.feature_repository_record_collection.external.albums.models.AlbumsRepositoryModel
import com.example.feature_viewmodel_record_collection.external.models.RecordVMModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RecordVMAndRepositoryModelConverterTest {

    @Test
    fun convert_RecordVMModelToRepositoryModel_correctContent() {

        //assemble
        val converter = RecordVMAndRepositoryModelConverter()
        val recordVMModel = RecordVMModel(
            id = 1,
            artist = "The Mighty Bobbins",
            name = "Bobbins Bounce"
        )

        //act
        val actual = converter.convert(recordVMModel)

        //assert
        assertEquals(recordVMModel.id, actual.id)
        assertEquals(recordVMModel.artist, actual.artist)
        assertEquals(recordVMModel.name, actual.name )
    }

    @Test
    fun convert_repositoryModelToVMModel_correctContent() {

        //assemble
        val converter = RecordVMAndRepositoryModelConverter()
        val albumsRepositoryModel = AlbumsRepositoryModel(
            id = 1,
            artist = "The Mighty Bobbins",
            name = "Bobbins Bounce"
        )

        //act
        val actual = converter.convert(albumsRepositoryModel)

        //assert
        assertEquals(albumsRepositoryModel.id, actual.id)
        assertEquals(albumsRepositoryModel.artist, actual.artist)
        assertEquals(albumsRepositoryModel.name, actual.name )
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.feature_viewmodel_record_collection.test", appContext.packageName)
    }
}