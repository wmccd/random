package com.example.feature_repository_record_collection.internal

import com.example.core_database.external.albums.models.AlbumDataSourceModel
import com.example.feature_repository_record_collection.external.albums.models.AlbumsRepositoryModel

internal class AlbumDataSourceAndRepositoryModelConverter {

    fun convert( dataSourceModel: AlbumDataSourceModel): AlbumsRepositoryModel{
        return AlbumsRepositoryModel(
            id =  dataSourceModel.albumId,
            artist = dataSourceModel.albumArtist,
            name = dataSourceModel.albumName
        )
    }

    fun convert( albumsRepositoryModel: AlbumsRepositoryModel): AlbumDataSourceModel{
        return AlbumDataSourceModel(
            albumId = albumsRepositoryModel.id,
            albumArtist = albumsRepositoryModel.artist,
            albumName = albumsRepositoryModel.name
        )
    }

}