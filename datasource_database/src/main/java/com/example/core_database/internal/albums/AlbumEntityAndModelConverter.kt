package com.example.core_database.internal.albums

import com.example.core_database.external.albums.models.AlbumDataSourceModel

internal class AlbumEntityAndModelConverter {

    fun convert(entity: AlbumEntity) = AlbumDataSourceModel(
        albumId = entity.albumId,
        albumArtist = entity.albumArtist,
        albumName = entity.albumName
    )

    fun convert(model: AlbumDataSourceModel) = AlbumEntity(
        albumId = model.albumId,
        albumArtist = model.albumArtist,
        albumName = model.albumName
    )

}