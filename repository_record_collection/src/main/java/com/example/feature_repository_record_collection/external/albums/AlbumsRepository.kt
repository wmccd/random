package com.example.feature_repository_record_collection.external.albums

import com.example.core_database.external.albums.iAlbumsDataSource
import com.example.core_database.external.albums.models.AlbumDataSourceModel
import com.example.feature_repository_record_collection.external.albums.models.AlbumsRepositoryModel
import com.example.feature_repository_record_collection.internal.AlbumDataSourceAndRepositoryModelConverter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AlbumsRepository(
    private val dataSource: iAlbumsDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : iAlbumsRepository {

    // Responsibility
    // the production implementation of iAlbumsRepository
    // communicate with appropriate data sources to utilise data for this feature
    // do not expose the internals of the solution
    // perform any transformations that make the set/get objects more convenient for consumption
    // accepts CoroutineDispatcher to assist with testing
    // accepts an iAlbumsDataSource parameter

    private val scope = CoroutineScope(dispatcher)
    private val converter = AlbumDataSourceAndRepositoryModelConverter()

    override val allAlbums: Flow<List<AlbumsRepositoryModel>> = dataSource.fetchAllStream.map { dataSourceList ->
        val repositoryList = arrayListOf<AlbumsRepositoryModel>()

        dataSourceList.forEach {
            repositoryList.add(converter.convert(it))
        }
        repositoryList
    }
    override val allArtists: Flow<List<String>> = dataSource.fetchAllArtistsStream
    override val albumCount: Flow<Int> = dataSource.fetchAlbumCountStream

    override suspend fun insertAlbum(albumsRepositoryModel: AlbumsRepositoryModel) = withContext(dispatcher){
        dataSource.insert(
            albumDataSourceModel = converter.convert(albumsRepositoryModel)
        )
    }

    override suspend fun updateAlbum(albumsRepositoryModel: AlbumsRepositoryModel) = withContext(dispatcher){
        var count = 0
        val a =  scope.async{
            count =  dataSource.update(
                albumDataSourceModel = converter.convert(albumsRepositoryModel)
            )
        }
        a.await()
        count;
    }

    override suspend fun deleteAlbum(albumId: Int)= withContext(dispatcher){
        var count = 0
        val a =  scope.async{
            count =  dataSource.deleteByAlbumId(albumId = albumId)
        }
        a.await()
        count;
    }

    override suspend fun deleteAllAlbums() = withContext(dispatcher){
        dataSource.deleteAll()
    }
}