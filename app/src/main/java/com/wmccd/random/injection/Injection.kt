package com.wmccd.random.injection

import com.example.core_database.external.albums.AlbumsDataSource
import com.example.core_database.external.albums.iAlbumsDataSource
import com.example.feature_repository_record_collection.external.albums.AlbumsRepository
import com.example.feature_repository_record_collection.external.albums.iAlbumsRepository
import com.example.feature_repository_record_collection.external.favourites.FavouritesRepository
import com.example.feature_repository_record_collection.external.favourites.iFavouritesRepository
import com.example.feature_viewmodel_record_collection.external.RecordCollectionViewModel
import com.example.feature_viewmodel_record_collection.external.iRecordCollectionViewModel
import com.wmccd.core_datastore.external.favourites.FavouritesDataSource
import com.wmccd.core_datastore.external.favourites.iFavouritesDataSource
import com.wmccd.random.MyApp

class Injection {

    val albumsDataSource : iAlbumsDataSource
    val favouritesDataSource: iFavouritesDataSource
    val albumsRepository: iAlbumsRepository
    val favouritesRepository: iFavouritesRepository
    val recordCollectionViewModel: iRecordCollectionViewModel

    init {
        albumsDataSource = AlbumsDataSource(context = MyApp.context)
        favouritesDataSource = FavouritesDataSource(context = MyApp.context)
        albumsRepository = AlbumsRepository(
            dataSource = albumsDataSource
        )
        favouritesRepository = FavouritesRepository(
            dataSource = favouritesDataSource
        )
        recordCollectionViewModel = RecordCollectionViewModel(
            albumsRepository = albumsRepository,
            favouritesRepository = favouritesRepository
        )

    }




}