package com.wmccd.core_datastore.store

import kotlinx.coroutines.flow.Flow

interface iFavouritesDataStore {

    // Responsibility | save and retrieve a string that hold a list of favourite ids

    val getFavourites: Flow<String>
    suspend fun saveFavourites(favouritesIds: String)
    suspend fun clearFavourites()
}