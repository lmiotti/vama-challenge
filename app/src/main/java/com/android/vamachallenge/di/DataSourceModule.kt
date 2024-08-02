package com.android.vamachallenge.di

import com.android.vamachallenge.data.db.datasource.AlbumLocalDataSource
import com.android.vamachallenge.data.db.datasource.AlbumLocalDataSourceImpl
import com.android.vamachallenge.data.network.datasource.AlbumRemoteDataSource
import com.android.vamachallenge.data.network.datasource.AlbumRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindAlbumRemoteDataSource(albumRemoteDataSourceImpl: AlbumRemoteDataSourceImpl): AlbumRemoteDataSource

    @Binds
    abstract fun bindAlbumLocalDataSource(albumLocalDataSourceImpl: AlbumLocalDataSourceImpl): AlbumLocalDataSource
}