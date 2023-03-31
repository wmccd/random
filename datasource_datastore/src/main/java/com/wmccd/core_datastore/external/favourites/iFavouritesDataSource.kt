package com.wmccd.core_datastore.external.favourites

import kotlinx.coroutines.flow.Flow

interface iFavouritesDataSource {

    // Responsibility
    // defines the contract of behaviour with a data source (production or mocked)
    // public so that higher level classes can inject mocked implementations

    val getFavourites: Flow<List<String>>
    suspend fun toggleFavourite(id: String, favourite: Boolean )
    suspend fun clearFavourites()
}