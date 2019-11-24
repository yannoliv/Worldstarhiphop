package com.example.worldstarhiphop.network

data class Track(
    val id: Int,
    val readable:Boolean,
    val title: String,
    val title_short: String,
    val title_version:String,
    val link: String,
    val duration: Int,
    val rank: Int,
    val explicit_lyrics: Boolean,
    val explicit_cover: Int,
    val preview: String,
    val contributors: List<Artist>,
    val total: Int,
    val next: String
)