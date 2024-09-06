package com.example.steamgameexplorer.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SteamGameResults(
     val featured_win: List<SteamGame> //featured windows games in the API call.
)
