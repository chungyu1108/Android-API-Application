package com.example.steamgameexplorer.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SteamService {
    @GET("api/featured/")
    fun getFeaturedGames(
    ): Call<SteamGameResults>

    companion object {
        private const val URL = "https://store.steampowered.com/"

        fun create(): SteamService {
            return Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(SteamService::class.java)
        }
    }
}