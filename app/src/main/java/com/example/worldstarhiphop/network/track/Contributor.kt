package com.example.worldstarhiphop.network.track

import com.squareup.moshi.Json

data class Contributor(
    val id: Int,
    val name: String,
    val link: String,
    val share: String,
    val picture: String,
    @Json(name = "picture_small") val pictureSmall: String,
    @Json(name = "picture_medium") val pictureMedium: String,
    @Json(name = "picture_big") val pictureBig: String,
    @Json(name = "picture_xl") val pictureXl: String,
    val radio: Boolean,
    val tracklist: String,
    val type: String,
    val role: String
)