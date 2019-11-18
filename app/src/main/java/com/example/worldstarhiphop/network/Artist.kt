package com.example.worldstarhiphop.network

import com.squareup.moshi.Json

data class Artist(
    val id: Int,
    val name: String,
    val link: String,
    val share: String,
    val picture: String,
    @Json(name = "picture_small") val pictureSmall: String,
    @Json(name = "picture_medium") val pictureMedium: String,
    @Json(name = "picture_big") val pictureBig: String,
    @Json(name = "picture_xl") val pictureXl: String,
    @Json(name = "nb_album") val nbAlbum: Int,
    @Json(name = "nb_fan") val nbFan: Int,
    val radio: Boolean,
    val tracklist: String,
    val type: String
)