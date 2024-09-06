package com.example.steamgameexplorer.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SteamGame(
    @Json(name = "name") val name: String,
    @Json(name = "small_capsule_image") var img_url_small: String,
    @Json(name = "original_price") val price: Int ?=null,
    @Json(name = "final_price") val dis_price: Int,
    @Json(name = "discount_percent") val dis_percent: Int,

    var isFavorite: Boolean = false
)
