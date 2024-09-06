package com.example.steamgameexplorer.ui.favorites

import FavoriteGame
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.steamgameexplorer.R
import com.example.steamgameexplorer.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        FirestoreHelper.getAllGames().addOnSuccessListener { querySnapshot ->
            val games = querySnapshot.documents.map { doc -> FavoriteGame(doc) }
            val favoritedGames = games.filter { it.isFavorite }
            recyclerView.adapter = GameAdapter(favoritedGames)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

/*
package com.example.steamgameexplorer.ui.favorites




import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.steamgameexplorer.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {

    val dummyData: List<Game> = listOf(
        Game(
            id = "1",
            imageUrl = "https://dummyimage.com/600x400/000/fff",
            isFavorite = false,
            title = "Game 1"
        ),
        Game(
            id = "2",
            imageUrl = "https://dummyimage.com/600x400/000/fff",
            isFavorite = true,
            title = "Game 2"
        ),
        Game(
            id = "3",
            imageUrl = "https://dummyimage.com/600x400/000/fff",
            isFavorite = false,
            title = "Game 3"
        )
    )

    val games: List<Game> = dummyData
    val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = GameAdapter(games)

    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favoritesViewModel =
            ViewModelProvider(this).get(FavoritesViewModel::class.java)

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        favoritesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

*/