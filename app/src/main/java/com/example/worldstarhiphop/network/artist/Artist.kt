package com.example.worldstarhiphop.network.artist

import com.squareup.moshi.Json

data class Artist(
    val id: Int,
    val name: String,
    val picture: String,
    @Json(name = "picture_small") val pictureSmall: String,
    @Json(name = "picture_medium") val pictureMedium: String,
    @Json(name = "picture_big") val pictureBig: String,
    @Json(name = "picture_xl") val pictureXl: String,
    val radio: Boolean,
    val tracklist: String,
    val type: String
)
