package com.example.itunesassignment.model.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ItunesAPI {
    //search
    // ?term=rock
    // &amp;media=music
    // &amp;entity=song
    // &amp;limit=50

    // https://itunes.apple.com/search?term=pop&amp;media=music&amp;entity=song&amp;limit=50
    // https://itunes.apple.com/search?term=rock&media=music&entity=song&limit=50

    @GET(ItunesNetwork.ENDPOINT)
    fun getMusic(
        @Query("term") musicTerm: String,
        @Query("&amp;media")musicMedia: String = "music",
        @Query("&amp;entity") musicEntity: String = "song",
        @Query("&amp;limit") musicLimit: Int = 50
    ): Call<ItunesResponse>
}