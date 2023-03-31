package com.example.feature_repository_record_collection.external.favourites

import kotlinx.coroutines.flow.Flow

interface iFavouritesRepository {

    // Responsibility
    // defines the contract of behaviour with a data source (production or mocked)
    // public so that higher level classes can inject mocked implementations

    val favouritesStream : Flow<List<String>>
    suspend fun toggleFavourite(id: String, favourite: Boolean )
}