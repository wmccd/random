package com.wmccd.core_datastore.external

import kotlinx.coroutines.flow.Flow

interface iFavouritesDataSource {

    // Responsibility | save and retrieve a string that hold a list of favourite ids

    val getFavourites: Flow<String>
    suspend fun saveFavourites(favouritesIds: String)
    suspend fun clearFavourites()
}