package com.android.vamachallenge.data.db.datasource

import com.android.vamachallenge.data.db.model.AlbumDB
import com.android.vamachallenge.data.network.model.AlbumApiResponse
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import javax.inject.Inject

class AlbumLocalDataSourceImpl @Inject constructor(
    private val db: Realm
): AlbumLocalDataSource {
    override fun getAlbums(): List<AlbumDB> {
        return db.query<AlbumDB>().find()
    }

    override fun saveAlbums(albums: List<AlbumApiResponse>) {
        db.writeBlocking {
            albums.map { copyToRealm(it.toAlbumDB(), updatePolicy = UpdatePolicy.ALL) }
        }
    }
}
