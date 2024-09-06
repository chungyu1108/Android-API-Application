package com.example.steamgameexplorer.ui.favorites

import FavoriteGame
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.steamgameexplorer.R

class FavoritesAdapter(
    private val favoriteGames: List<FavoriteGame>,
    private val listener: OnFavoriteGameClickListener
) : RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    interface OnFavoriteGameClickListener {
        fun onFavoriteGameClicked(url: String)
    }

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.gameNameTextView)
        // Ensure you have a TextView with the ID gameNameTextView in your item layout

        fun bind(game: FavoriteGame, clickListener: OnFavoriteGameClickListener) {
            nameTextView.text = game.title
            itemView.setOnClickListener {
                clickListener.onFavoriteGameClicked(game.steamID)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_game, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoriteGames[position], listener)
    }

    override fun getItemCount() = favoriteGames.size
}
