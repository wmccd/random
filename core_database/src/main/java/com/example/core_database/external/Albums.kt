package com.example.core_database.external

import com.example.core_database.external.models.AlbumModel
import kotlinx.coroutines.flow.Flow

class Albums(private val dataSource: iAlbumsDataSource) {

    // Responsibility
    // public gateway for accessing the album data without
    // accepts an iAlbumsDataSource parameter

    val fetchAllStream: Flow<List<AlbumModel>> = dataSource.fetchAllStream
    val fetchAllArtistsStream: Flow<List<String>> = dataSource.fetchAllArtistsStream
    val fetchAlbumCountStream: Flow<Int> = dataSource.fetchAlbumCountStream

    suspend fun insert(albumModel: AlbumModel){
        dataSource.insert(albumModel = albumModel)
    }

    suspend fun update(albumModel: AlbumModel): Int{
        return dataSource.update(albumModel = albumModel)
    }

    suspend fun deleteByAlbumId(albumId: Int): Int{
        return dataSource.deleteByAlbumId(albumId = albumId)
    }

    suspend fun deleteAll(){
        dataSource.deleteAll()
    }
}