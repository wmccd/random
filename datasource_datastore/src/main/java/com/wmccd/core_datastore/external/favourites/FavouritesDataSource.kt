package com.wmccd.core_datastore.external.favourites

import android.content.Context
import android.util.Log
import com.wmccd.core_datastore.internal.favourites.FavouritesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class FavouritesDataSource(private val context: Context): iFavouritesDataSource {

    // Responsibility
    // the production implementation of iFavouritesDataSource
    // does not expose the internals of the solution
    // interacts with the DataStore object but does not expose this
    // performs any transformations necessary hide the internals
    // performs any transformations necessary to ensure data is available conveniently through basic types or public models
    // in this case converts the divider separated string into a list

    private val favouritesDataStore = FavouritesDataStore(context = context)

    companion object{
        const val DIVIDER = "|"
    }

    override val getFavourites : Flow<List<String>> = favouritesDataStore.getFavourites.map { it ->
        val list = arrayListOf<String>()

        when(it.length){
            0 -> {}
            else -> {
                it.split(DIVIDER).filter{ it.isNotEmpty() }.forEach { item ->
                    list.add(item)
                }
            }
        }
        list
    }

    override suspend fun toggleFavourite(id: String, favourite: Boolean ) {

        when (favourite) {
            true -> {
                val favs = favouritesDataStore.getFavourites.first()
                favouritesDataStore.saveFavourites("$id$DIVIDER$favs")
            }
            false -> {
                val original = favouritesDataStore.getFavourites.first()
                val removing = "$id$DIVIDER"
                Log.d("XXX", "original:<$original> removing:<$removing>")
                val updated = original.replace(removing, "")
                Log.d("XXX", "updated:<$updated> removing:<$removing>")
                favouritesDataStore.saveFavourites("$updated")

            }
        }
    }

    override suspend fun clearFavourites(){
        favouritesDataStore.clearFavourites()
    }
}