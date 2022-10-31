package com.example.itunesassignment.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object ItunesNetwork {


    private const val BASE_URL = "https://itunes.apple.com/"
    const val ENDPOINT = "search"

    val itunesAPI: ItunesAPI by lazy{
        initRetrofit().create(ItunesAPI::class.java)
    }

    private fun initRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}