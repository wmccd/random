package com.wmccd.core_datastore.external

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class Favourites(private val dataSource: iFavouritesDataSource) {

    // Responsibility
    // perform any transformations that make the set/get objects more convenient for consumption
    // in this instance maps a pipe separated string into a list
    // accepts an iFavouritesDataStore parameter

    companion object{
        const val DIVIDER = "|"
    }

    val favouritesStream : Flow<List<String>> = dataSource.getFavourites.map { it ->
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

    suspend fun toggleFavourite(id: String, favourite: Boolean ) {

        when (favourite) {
            true -> {
                val favs = dataSource.getFavourites.first()
                dataSource.saveFavourites("$id$DIVIDER$favs")
            }
            false -> {
                val original = dataSource.getFavourites.first()
                val removing = "$id$DIVIDER"
                Log.d("XXX", "original:<$original> removing:<$removing>")
                val updated = original.replace(removing, "")
                Log.d("XXX", "updated:<$updated> removing:<$removing>")
                dataSource.saveFavourites("$updated")

            }
        }
    }
}