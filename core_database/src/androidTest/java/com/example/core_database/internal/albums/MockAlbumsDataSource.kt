package com.example.core_database.internal.albums

import com.example.core_database.external.iAlbumsDataSource
import com.example.core_database.external.models.AlbumModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class MockAlbumsDataSource: iAlbumsDataSource {

    var lastInvoke = ""

    override val fetchAllStream: Flow<List<AlbumModel>>
        get() = flow{
            emit(
                listOf(
                    AlbumModel(1, "artist1", "album1"),
                    AlbumModel(2, "artist2", "album2"),
                    AlbumModel(3, "artist3", "album3")
                )
            )
        }

    override val fetchAllArtistsStream: Flow<List<String>>
        get() = flow{ emit(
            listOf(
                "Bobbins",
                "The Mighty Bobbins",
                "Bob Bobbins"
            ) )}

    override val fetchAlbumCountStream: Flow<Int>
        get() = flow { emit(99) }

    override suspend fun insert(albumModel: AlbumModel) {
        lastInvoke = "insert"
    }

    override suspend fun update(albumModel: AlbumModel): Int {
        return 4
    }

    override suspend fun deleteByAlbumId(albumId: Int): Int {
        return 5
    }

    override suspend fun deleteAll() {
        lastInvoke = "deleteAll"
    }
}