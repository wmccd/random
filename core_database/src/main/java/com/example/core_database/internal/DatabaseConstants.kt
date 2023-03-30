package com.example.core_database.internal

internal class DatabaseConstants {

    object AlbumsDatabase{
        const val NAME = "music.db"
        const val VERSION = 1

        object Tables{
            object Albums{
                const val NAME = "albums"
            }
        }
    }
}
