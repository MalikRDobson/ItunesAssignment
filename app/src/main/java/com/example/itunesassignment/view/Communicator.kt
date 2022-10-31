package com.example.itunesassignment.view

import com.example.itunesassignment.model.remote.ItunesResponse

interface Communicator {
    fun getMusic(genre: String)
}