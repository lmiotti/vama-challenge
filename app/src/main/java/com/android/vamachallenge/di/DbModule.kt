package com.android.vamachallenge.di

import com.android.vamachallenge.data.db.model.AlbumDB
import com.android.vamachallenge.data.db.model.GenreDB
import com.android.vamachallenge.data.network.model.AlbumApiResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    fun provideRealmDB(): Realm {
        val config = RealmConfiguration.create(schema = setOf(AlbumDB::class, GenreDB::class))
        return Realm.open(config)
    }
}