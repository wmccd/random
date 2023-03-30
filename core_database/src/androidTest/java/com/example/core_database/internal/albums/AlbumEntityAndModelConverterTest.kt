package com.example.core_database.internal.albums

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.core_database.external.models.AlbumModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AlbumEntityAndModelConverterTest {

    @Test
    fun convert_modelBlanks_entityBlanks(){

        //assemble
        val converter = AlbumEntityAndModelConverter()
        val model = AlbumModel()
        val expected = AlbumEntity()

        //act
        val actual = converter.convert(model)

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun convert_entityBlanks_modelBlanks(){

        //assemble
        val converter = AlbumEntityAndModelConverter()
        val expected = AlbumModel()
        val entity = AlbumEntity()

        //act
        val actual = converter.convert(entity)

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun convert_modelFilled_entityFilled(){

        //assemble
        val converter = AlbumEntityAndModelConverter()
        val model = AlbumModel(
            albumId = 3,
            albumArtist = "Bobbins",
            albumName =  "Mighty"
        )
        val expected = AlbumEntity(
            albumId = 3,
            albumArtist = "Bobbins",
            albumName =  "Mighty"
        )

        //act
        val actual = converter.convert(model)

        //assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun convert_entityFilled_modelFilled(){

        //assemble
        val converter = AlbumEntityAndModelConverter()
        val expected = AlbumModel(
            albumId = 3,
            albumArtist = "Bobbins",
            albumName =  "Mighty"
        )
        val entity = AlbumEntity(
            albumId = 3,
            albumArtist = "Bobbins",
            albumName =  "Mighty"
        )

        //act
        val actual = converter.convert(entity)

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
