package com.example.favfoodroom.network

import com.squareup.moshi.Json


data class Photo(
    @Json(name = "created_at") val createdAt: String="",
    val urls:PhotoUrls
)

data class PhotoUrls(
    val regular:String=""
)

