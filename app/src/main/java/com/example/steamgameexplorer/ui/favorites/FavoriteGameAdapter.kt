package com.example.steamgameexplorer.ui.favorites

import FavoriteGame
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.steamgameexplorer.R
import com.bumptech.glide.Glide
import kotlin.math.log

class GameAdapter(private val games: List<FavoriteGame>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    inner class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        // val favoriteCheckBox: CheckBox = view.findViewById(R.id.favoriteCheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.titleTextView.text = game.title
        // log everything to see if it's working

        if (game.imageUrl != null) {
            // Load image from URL into ImageView using a library like Glide or Picasso
            // For example, with Glide:
            Glide.with(holder.imageView.context).load(game.imageUrl).into(holder.imageView)
        } else {
            // Set a placeholder image or clear the ImageView if the URL is null
            holder.imageView.setImageDrawable(null)
        }

        // Set an onClick listener for the card
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://store.steampowered.com/app/${game.steamID}"))

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = games.size
}