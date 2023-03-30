package com.example.core_database.external

import android.content.Context
import com.example.core_database.external.models.AlbumModel
import com.example.core_database.internal.AlbumDatabase
import com.example.core_database.internal.albums.AlbumEntityAndModelConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AlbumsDataSource(private val context: Context): iAlbumsDataSource {

    private val albumsStore = AlbumDatabase.instance(context = context).albumsStore()
    private val converter = AlbumEntityAndModelConverter()

    override val fetchAllStream : Flow<List<AlbumModel>> = albumsStore.fetchAll().map { itemList ->
        val modelList = arrayListOf<AlbumModel>()
        itemList.forEach { modelList.add(converter.convert(it) ) }
        modelList
    }

    override val fetchAllArtistsStream: Flow<List<String>> = albumsStore.fetchAllArtists().map { itemList ->
        itemList
    }

    override val fetchAlbumCountStream: Flow<Int> = albumsStore.fetchAlbumCount().map {
        it
    }

    override suspend fun insert(albumModel: AlbumModel) {
        albumsStore.insert(converter.convert(albumModel))
    }

    override suspend fun update(albumModel: AlbumModel): Int {
        return albumsStore.update(converter.convert(albumModel))
    }

    override suspend fun deleteByAlbumId(albumId: Int): Int {
        return albumsStore.deleteByTripId(albumId)
    }

    override suspend fun deleteAll() {
        albumsStore.deleteAll()
    }


}