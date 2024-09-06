package com.example.steamgameexplorer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.steamgameexplorer.R
import com.example.steamgameexplorer.SteamGameListAdapter
import com.example.steamgameexplorer.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private val steamAdapter = SteamGameListAdapter()
    private lateinit var steamListRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        steamListRV = view.findViewById(R.id.rv_featured_list)
        steamListRV.layoutManager = LinearLayoutManager(requireContext())
        steamListRV.setHasFixedSize(true)
        steamListRV.adapter = steamAdapter


        viewModel.games.observe(viewLifecycleOwner) { featured ->
            if (featured != null) {
                steamAdapter.updateRepoList(featured)
                steamListRV.visibility = View.VISIBLE
                steamListRV.scrollToPosition(0)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadFeaturedGames()

    }

}

