package com.example.feature_repository_record_collection.external.albums

import com.example.feature_repository_record_collection.external.albums.models.AlbumsRepositoryModel
import kotlinx.coroutines.flow.Flow

interface iAlbumsRepository {

    // Responsibility
    // defines the contract of behaviour with a data source (production or mocked)
    // public so that higher level classes can inject mocked implementations

    val allAlbums: Flow<List<AlbumsRepositoryModel>>
    val allArtists: Flow<List<String>>
    val albumCount: Flow<Int>
    suspend fun insertAlbum(albumsRepositoryModel: AlbumsRepositoryModel)
    suspend fun updateAlbum(albumsRepositoryModel: AlbumsRepositoryModel): Int
    suspend fun deleteAlbum(albumId: Int): Int
    suspend fun deleteAllAlbums()
}