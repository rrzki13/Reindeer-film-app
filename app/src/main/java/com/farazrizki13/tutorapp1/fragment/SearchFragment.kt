package com.farazrizki13.tutorapp1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.farazrizki13.tutorapp1.R
import com.farazrizki13.tutorapp1.`interface`.NavigationHost
import com.farazrizki13.tutorapp1.adapter.SearchGenreAdapter
import com.farazrizki13.tutorapp1.model.SearchGenreModel
import com.luseen.spacenavigation.SpaceNavigationView
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_fragment.view.*

class SearchFragment : Fragment() {

    private lateinit var searchGenreAdapter: SearchGenreAdapter

    private val  searchGenreSource = listOf(
        SearchGenreModel("Action\nMovies", R.color.indigo),
        SearchGenreModel("Comedy\nMovies", R.color.colorAccent),
        SearchGenreModel("Adventure\nMovies", R.color.teal),
        SearchGenreModel("Horror\nMovies", R.color.red),
        SearchGenreModel("Sci-Fi\nMovies", R.color.purple),
        SearchGenreModel("Family\nMovies", R.color.cyan),
        SearchGenreModel("Animation\nMovies", R.color.blue),
        SearchGenreModel("Drama\nMovies", R.color.yellow),
        SearchGenreModel("Fantasy\nMovies", R.color.pink),
        SearchGenreModel("Musical\nMovies", R.color.green)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_fragment, container,false)
        val cardSearch = view.card_search_film
        val space : SpaceNavigationView = activity!!.findViewById(R.id.space)
        space.visibility = View.VISIBLE

        cardSearch.setOnClickListener {
            (activity as NavigationHost).navigateTo(SearchEditTextFragment(),
                addToBackstack = true,
                fade = false,
                search = true
            )
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_search_genre.apply {
            layoutManager = GridLayoutManager(view.context, 2, GridLayoutManager.VERTICAL, false)
            searchGenreAdapter = SearchGenreAdapter(searchGenreSource)
            adapter = searchGenreAdapter
        }
    }

}