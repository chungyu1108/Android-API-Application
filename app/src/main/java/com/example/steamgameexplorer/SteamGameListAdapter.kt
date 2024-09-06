package com.example.steamgameexplorer

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.steamgameexplorer.data.SteamGame
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
class SteamGameListAdapter : RecyclerView.Adapter<SteamGameListAdapter.SteamGameViewHolder>() {
    var steamGameList = listOf<SteamGame>()

    fun updateRepoList(newRepoList: List<SteamGame>?) {
        notifyItemRangeRemoved(0, steamGameList.size)
        steamGameList = newRepoList ?: listOf()
        notifyItemRangeInserted(0, steamGameList.size)
    }

    override fun getItemCount() = steamGameList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SteamGameViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.steam_game_item, parent, false)
        return SteamGameViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: SteamGameViewHolder, position: Int) {
        holder.bind(steamGameList[position])
    }

    class SteamGameViewHolder(itemView: View, private val adapter: SteamGameListAdapter) : RecyclerView.ViewHolder(itemView) {
        private var currentSteamGame: SteamGame? = null
        private val nameTV: TextView = itemView.findViewById(R.id.tv_name)
        private val iconTV: ImageView = itemView.findViewById(R.id.tv_featured_icon)
        private val heartIV: ImageView = itemView.findViewById(R.id.iv_heart)
        private val cardView: MaterialCardView = itemView.findViewById(R.id.material_card_view)
        init {
            cardView.setOnClickListener {
                currentSteamGame?.let { game ->
                    game.isFavorite = !game.isFavorite
                    if (game.isFavorite) {
                        heartIV.setImageResource(R.drawable.filled_heart)
                    } else {
                        heartIV.setImageResource(R.drawable.unfilled_heart)
                    }
                    FirestoreHelper.updateGameFavoriteStatus(game.name, game.isFavorite, game.img_url_small)
                    adapter.notifyItemChanged(adapterPosition)
                }
            }
        }
        fun bind(steamGame: SteamGame) {
            val ctx = itemView.context
            currentSteamGame = steamGame
            nameTV.text = steamGame.name
            Log.d("steamname", steamGame.name)

            Glide.with(ctx)
                .load(currentSteamGame!!.img_url_small)
                .into(iconTV)

            if (steamGame.isFavorite) {
                heartIV.setImageResource(R.drawable.filled_heart)
            } else {
                heartIV.setImageResource(R.drawable.unfilled_heart)
            }
        }
        }
    }
