package com.android.vamachallenge.data.db.model

import com.android.vamachallenge.domain.model.Album
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class AlbumDB : RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var artistName: String = ""
    var id: String = ""
    var name: String = ""
    var releaseDate: String = ""
    var kind: String = ""
    var artistId: String = ""
    var artistUrl: String = ""
    var artworkUrl: String = ""
    var genres: RealmList<GenreDB> = realmListOf()
    var url: String = ""
    var copyright: String = "";

    fun toAlbum(): Album =
        Album(
            name = this.name,
            artistName = this.artistName,
            thumbnail = this.artworkUrl,
            genres = this.genres.map { it.name },
            releaseDate = this.releaseDate,
            copyright = this.copyright,
            url = this.url
        )
}
