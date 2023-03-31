package com.example.core_database.internal.albums

import androidx.room.*
import com.example.core_database.internal.DatabaseConstants
import kotlinx.coroutines.flow.Flow

@Dao
internal interface AlbumsStore {
    @Query("SELECT * FROM ${DatabaseConstants.AlbumsDatabase.Tables.Albums.NAME} ORDER BY albumArtist ASC")
    fun fetchAll(): Flow<List<AlbumEntity>>

    @Query("SELECT DISTINCT albumArtist FROM ${DatabaseConstants.AlbumsDatabase.Tables.Albums.NAME} ORDER BY albumArtist ASC")
    fun fetchAllArtists(): Flow<List<String>>

    @Query("SELECT * FROM ${DatabaseConstants.AlbumsDatabase.Tables.Albums.NAME} WHERE albumArtist < :albumArtist ORDER BY albumName ASC")
    fun fetchAlbumsByArtist(albumArtist: String): Flow<List<AlbumEntity>>

    @Query("SELECT COUNT(albumId) FROM ${DatabaseConstants.AlbumsDatabase.Tables.Albums.NAME}")
    fun fetchAlbumCount(): Flow<Int>

    @Insert
    suspend fun insert(albumEntity: AlbumEntity)

    @Update
    suspend fun update(albumEntity: AlbumEntity): Int

    @Query("DELETE FROM  ${DatabaseConstants.AlbumsDatabase.Tables.Albums.NAME} WHERE albumId = :albumId")
    suspend fun deleteByAlbumId(albumId: Int): Int

    @Query("DELETE FROM ${DatabaseConstants.AlbumsDatabase.Tables.Albums.NAME}")
    suspend fun deleteAll()
}

