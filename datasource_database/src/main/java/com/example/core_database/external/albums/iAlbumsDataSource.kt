package com.example.core_database.external.albums

import com.example.core_database.external.albums.models.AlbumDataSourceModel
import kotlinx.coroutines.flow.Flow

interface iAlbumsDataSource {

    // Responsibility
    // defines the contract of behaviour with a data source (production or mocked)
    // public so that higher level classes can inject mocked implementations

    val fetchAllStream : Flow<List<AlbumDataSourceModel>>
    val fetchAllArtistsStream: Flow<List<String>>
    val fetchAlbumCountStream: Flow<Int>

    suspend fun insert(albumDataSourceModel: AlbumDataSourceModel)
    suspend fun update(albumDataSourceModel: AlbumDataSourceModel): Int
    suspend fun deleteByAlbumId(albumId: Int): Int
    suspend fun deleteAll()
}