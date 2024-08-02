package com.android.vamachallenge.data.db.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class GenreDB : RealmObject {
    @PrimaryKey
    var genreId: String = ""
    var name: String = ""
    var url: String = ""
}