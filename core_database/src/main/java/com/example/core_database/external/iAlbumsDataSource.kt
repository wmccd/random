package com.example.core_database.external

import com.example.core_database.external.models.AlbumModel
import kotlinx.coroutines.flow.Flow

interface iAlbumsDataSource {

    // Responsibility | save and retrieve a data that holds album information

    val fetchAllStream : Flow<List<AlbumModel>>
    val fetchAllArtistsStream: Flow<List<String>>
    val fetchAlbumCountStream: Flow<Int>

    suspend fun insert(albumModel: AlbumModel)
    suspend fun update(albumModel: AlbumModel): Int
    suspend fun deleteByAlbumId(albumId: Int): Int
    suspend fun deleteAll()
}