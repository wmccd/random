package com.example.core_database.internal.albums

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.core_database.internal.DatabaseConstants

@Entity(
    tableName = DatabaseConstants.AlbumsDatabase.Tables.Albums.NAME,
    indices = [
        Index("albumId", unique = true),
        Index("albumArtist", unique = false)
    ]
)
internal data class AlbumEntity(
    @PrimaryKey (autoGenerate = true) val albumId: Long = 0,
    val albumArtist: String = "",
    val albumName: String = "",
)
