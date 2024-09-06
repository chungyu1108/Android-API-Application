package com.example.steamgameexplorer.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.steamgameexplorer.data.SteamGame
import com.example.steamgameexplorer.data.SteamGameResults
import com.example.steamgameexplorer.data.SteamService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val steamService = SteamService.create()
    private val _games = MutableLiveData<List<SteamGame>>()

    val games : LiveData<List<SteamGame>> = _games

    fun loadFeaturedGames(){
        viewModelScope.launch {
            steamService.getFeaturedGames().enqueue(object: Callback<SteamGameResults> {
                override fun onResponse(call: Call<SteamGameResults>, response: Response<SteamGameResults>) {
                    Log.d("MainActivity", "Status code: ${response.code()}")
                    Log.d("MainActivity", "Response body: ${response.body()}")
                    if (response.isSuccessful) {
                        _games.value = response.body()?.featured_win

                        // update the like status of the games from the database
                        FirestoreHelper.getAllGames().addOnSuccessListener { documents ->
                            val updatedGames = _games.value?.map { game: SteamGame ->
                                documents.find { it.getString("gameTitle") == game.name }?.let { document ->
                                    game.isFavorite = document.getBoolean("favorited") ?: false
                                    game.img_url_small = document.getString("imageUrl") ?: ""

                                    // log the image url
                                    Log.d("GameCardInfo", "Game Image URL: ${game.img_url_small}")
                                    val regex = Regex("/apps/(\\d+)")
                                    val matchResult = regex.find(game.img_url_small)
                                    val steamID = matchResult?.groups?.get(1)?.value
                                    Log.d("GameCardInfo", "Game ID: $steamID")
                                    game
                                } ?: game
                            } ?: emptyList()

                            _games.value = updatedGames
                        }
                    }
                }

                override fun onFailure(call: Call<SteamGameResults>, t: Throwable) {
                    Log.d("MainActivity", "Error making API call: ${t.message}")
                }
            })
        }
    }
}