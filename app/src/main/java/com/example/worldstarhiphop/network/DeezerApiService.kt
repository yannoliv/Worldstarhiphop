package com.example.worldstarhiphop.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
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
    @GET("genre/116/artists")
    fun getArtiesten(): Deferred<ArtistListObject>

    /*
    @GET("artist/{id}/top?limit=5")
    fun getTracksVanArtiest(@Path("id") id:Int): Deferred<TrackListObject>
    */

    @GET("artist/13/top?limit=5")
    fun getTracksVanArtiest(): Deferred<TrackListObject>
}


object DeezerAPI{
    val retrofitService : DeezerApiService by lazy{
        retrofit.create(DeezerApiService::class.java)
    }
}

// artist/{{ArtiestID}}/top?limit=5