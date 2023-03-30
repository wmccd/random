package com.wmccd.core_datastore.internal.favourites

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.wmccd.core_datastore.external.iFavouritesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


internal class FavouritesDataStore(private val context: Context): iFavouritesDataSource {

    // Responsibility |
    // the production implementation of iFavouritessDataStore
    // interacts with the DataStore itself but absolutely nothing else


    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Favourites")
        private val FAVOURITES = stringPreferencesKey("favourites")
    }

    override val getFavourites: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[FAVOURITES] ?: ""
    }

    override suspend fun saveFavourites(favouritesIds: String) {
        context.dataStore.edit { preferences ->
            preferences[FAVOURITES] = favouritesIds
        }
    }

    override suspend fun clearFavourites() {
        context.dataStore.edit { preferences ->
            preferences[FAVOURITES] = ""
        }
    }


}