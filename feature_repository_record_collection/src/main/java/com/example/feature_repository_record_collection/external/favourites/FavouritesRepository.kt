package com.example.feature_repository_record_collection.external.favourites

import android.util.Log
import com.wmccd.core_datastore.external.favourites.iFavouritesDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FavouritesRepository(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dataSource: iFavouritesDataSource
): iFavouritesRepository {

    // Responsibility
    // the production implementation of iFavouritesRepository
    // communicate with appropriate data sources to utilise data for this feature
    // do not expose the internals of the solution
    // perform any transformations that make the set/get objects more convenient for consumption
    // accepts CoroutineDispatcher to assist with testing
    // accepts an iFavouritesDataStore parameter

    override val favouritesStream : Flow<List<String>> = dataSource.getFavourites.map { it ->
        it
    }

    override suspend fun toggleFavourite(id: String, favourite: Boolean ) {
        withContext(dispatcher){
            dataSource.toggleFavourite(
                id = id,
                favourite = favourite
            )
        }
    }
}