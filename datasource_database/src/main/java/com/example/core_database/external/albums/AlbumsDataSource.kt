package com.example.core_database.external.albums

import android.content.Context
import com.example.core_database.external.albums.models.AlbumDataSourceModel
import com.example.core_database.internal.AlbumDatabase
import com.example.core_database.internal.albums.AlbumEntityAndModelConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AlbumsDataSource(private val context: Context): iAlbumsDataSource {

    // Responsibility |
    // the production implementation of iAlbumsDataSource
    // does not expose the internals of the solution
    // interacts with the Database object but does not expose this
    // performs any transformations necessary hide the internals
    // performs any transformations necessary to ensure data is available through basic types or public models
    // in this case transforms between the internal entity and public model via a converter class

    private val albumsStore = AlbumDatabase.instance(context = context).albumsStore()
    private val converter = AlbumEntityAndModelConverter()

    override val fetchAllStream : Flow<List<AlbumDataSourceModel>> = albumsStore.fetchAll().map { itemList ->
        val modelList = arrayListOf<AlbumDataSourceModel>()
        itemList.forEach { modelList.add(converter.convert(it) ) }
        modelList
    }

    override val fetchAllArtistsStream: Flow<List<String>> = albumsStore.fetchAllArtists().map { itemList ->
        itemList
    }

    override val fetchAlbumCountStream: Flow<Int> = albumsStore.fetchAlbumCount().map {
        it
    }

    override suspend fun insert(albumDataSourceModel: AlbumDataSourceModel) {
        albumsStore.insert(converter.convert(albumDataSourceModel))
    }

    override suspend fun update(albumDataSourceModel: AlbumDataSourceModel): Int {
        return albumsStore.update(converter.convert(albumDataSourceModel))
    }

    override suspend fun deleteByAlbumId(albumId: Int): Int {
        return albumsStore.deleteByAlbumId(albumId)
    }

    override suspend fun deleteAll() {
        albumsStore.deleteAll()
    }


}