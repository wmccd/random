package com.example.feature_repository_record_collection.external.albums

import com.example.core_database.external.albums.iAlbumsDataSource
import com.example.core_database.external.albums.models.AlbumDataSourceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class MockAlbumsDataSource: iAlbumsDataSource {

    var lastInvoke = ""

    override val fetchAllStream: Flow<List<AlbumDataSourceModel>>
        get() = flow{
            emit(
                listOf(
                    AlbumDataSourceModel(1, "artist1", "album1"),
                    AlbumDataSourceModel(2, "artist2", "album2"),
                    AlbumDataSourceModel(3, "artist3", "album3")
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

    override suspend fun insert(albumDataSourceModel: AlbumDataSourceModel) {
        lastInvoke = "insert"
    }

    override suspend fun update(albumDataSourceModel: AlbumDataSourceModel): Int {
        return 4
    }

    override suspend fun deleteByAlbumId(albumId: Int): Int {
        return 5
    }

    override suspend fun deleteAll() {
        lastInvoke = "deleteAll"
    }
}