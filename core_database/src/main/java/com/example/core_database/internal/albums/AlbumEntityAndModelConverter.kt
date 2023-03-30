package com.example.core_database.internal.albums

import com.example.core_database.external.models.AlbumModel

internal class AlbumEntityAndModelConverter {

    fun convert(entity: AlbumEntity) = AlbumModel(
        albumId = entity.albumId,
        albumArtist = entity.albumArtist,
        albumName = entity.albumName
    )

    fun convert(model: AlbumModel) = AlbumEntity(
        albumId = model.albumId,
        albumArtist = model.albumArtist,
        albumName = model.albumName
    )

}