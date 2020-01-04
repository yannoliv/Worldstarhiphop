package com.example.worldstarhiphop.network

import com.example.worldstarhiphop.network.artist.ArtistListObject
import com.example.worldstarhiphop.network.radio.RadiosListObject
import com.example.worldstarhiphop.network.track.TrackListArtist
import com.example.worldstarhiphop.network.track.TrackListRadio
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.deezer.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface DeezerApiService {
    /** Artist Fragment **/
    // Alle artiesten uit het genre Hip Hop
    @GET("genre/116/artists")
    fun getArtiesten(): Deferred<ArtistListObject>

    // Alle artiesten uit het genre Hip Hop, hun top 5 liedjes
    @GET("artist/{id}/top?limit=5")
    fun getTracksVanArtiest(@Path("id") id: Int): Deferred<TrackListArtist>

    // Alle radio's van het genre Hip Hop
    @GET("genre/116/radios")
    fun getRadios(): Deferred<RadiosListObject>

    // Alle artistTracks van de radio die megegeven is.
    @GET("radio/{id}/tracks")
    fun getTracksVanRadio(@Path("id") id: Int): Deferred<TrackListRadio>
}

object DeezerAPI {
    val retrofitService: DeezerApiService by lazy {
        retrofit.create(DeezerApiService::class.java)
    }
}

// artist/{{ArtiestID}}/top?limit=5
