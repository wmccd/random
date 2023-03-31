package com.example.core_database.internal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.core_database.internal.albums.AlbumEntity
import com.example.core_database.internal.albums.AlbumsStore

private const val DB_NAME = DatabaseConstants.AlbumsDatabase.NAME

@Database(
    entities = [AlbumEntity::class,],
    version = DatabaseConstants.AlbumsDatabase.VERSION
)
internal abstract class AlbumDatabase : RoomDatabase() {
    abstract fun albumsStore(): AlbumsStore

    companion object {
        fun instance(context: Context) = Room.databaseBuilder(context, AlbumDatabase::class.java, DB_NAME)
            .build()
    }
}