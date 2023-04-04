package com.example.feature_viewmodel_record_collection

import com.example.feature_repository_record_collection.external.albums.iAlbumsRepository
import com.example.feature_repository_record_collection.external.albums.models.AlbumsRepositoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockAlbumsRepository : iAlbumsRepository {

    private val list = arrayListOf<AlbumsRepositoryModel>()

    override val allAlbums: Flow<List<AlbumsRepositoryModel>>
        get() = flow {
            emit( list)
        }

    override val allArtists: Flow<List<String>>
        get() = flow {
            emit(list.map { it.artist })
        }

    override val albumCount: Flow<Int>
        get() = flow{list.size}

    override suspend fun insertAlbum(albumsRepositoryModel: AlbumsRepositoryModel) {
        list.add(albumsRepositoryModel)
    }

    override suspend fun updateAlbum(albumsRepositoryModel: AlbumsRepositoryModel): Int {
        val index = list.indexOfFirst{ it.id == albumsRepositoryModel.id }
        return if( index == -1){
            0
        }else{
            list[index] = albumsRepositoryModel
            1
        }
    }

    override suspend fun deleteAlbum(albumId: Int): Int {
        val originalSize = list.size
        list.removeIf { it.id == albumId.toLong()  }
        return originalSize - list.size
    }

    override suspend fun deleteAllAlbums() {}
}