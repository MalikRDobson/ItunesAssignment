package com.example.itunesassignment.model.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItunesResponse(
    val resultCount: Long,
    val results: List<ItunesInfo>,
): Parcelable

@Parcelize
data class ItunesInfo(
    val artistName: String,
    val collectionName: String,
    val previewUrl: String,
    val artworkUrl60: String,
    val trackPrice: Double,
): Parcelable