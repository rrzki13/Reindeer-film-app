package com.farazrizki13.tutorapp1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.farazrizki13.tutorapp1.R
import com.farazrizki13.tutorapp1.`interface`.NavigationHost
import com.farazrizki13.tutorapp1.adapter.SearchResultAdapter
import com.farazrizki13.tutorapp1.api.RetrofitClient
import com.farazrizki13.tutorapp1.model.SearchListModel
import com.farazrizki13.tutorapp1.model.SearchResultModel
import com.luseen.spacenavigation.SpaceNavigationView
import kotlinx.android.synthetic.main.search_edit_text_fragment.*
import kotlinx.android.synthetic.main.search_edit_text_fragment.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchEditTextFragment : Fragment() {

    private lateinit var searchResultAdapter: SearchResultAdapter
    private val list = ArrayList<SearchListModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_edit_text_fragment, container, false)
        val space : SpaceNavigationView = activity!!.findViewById(R.id.space)
        space.visibility = View.GONE
        view.et_search_movie.requestFocus()

        view.et_search_movie.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                closeAll()
                val value = view.et_search_movie.text
                showLoad()
                view.et_search_movie.clearFocus()

                searching(value.toString())

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        view.back_to_search.setOnClickListener {
            (activity as NavigationHost).navigateTo(SearchFragment(),
                addToBackstack = false,
                fade = false
            )
        }

        return view
    }

    private fun showLoad() {
        val searchImg = view?.findViewById<ImageView>(R.id.search_img)
        val tvSearchTitle = view?.findViewById<TextView>(R.id.tv_search_title)
        val tvSearchSubtitle = view?.findViewById<TextView>(R.id.tv_search_subtitle)
        val pbSearch = view?.findViewById<ProgressBar>(R.id.pb_search)

        searchImg?.visibility = View.GONE
        tvSearchTitle?.visibility = View.GONE
        tvSearchSubtitle?.tv_search_subtitle?.visibility = View.GONE
        pbSearch?.visibility = View.VISIBLE
    }

    private fun closeLoadSuccess() {
        val pbSearch = view?.findViewById<ProgressBar>(R.id.pb_search)
        val searchResult = view?.findViewById<TextView>(R.id.search_result)
        val rvSearchList = view?.findViewById<RecyclerView>(R.id.rv_search_list)

        pbSearch?.visibility = View.GONE
        searchResult?.visibility = View.VISIBLE
        rvSearchList?.visibility = View.VISIBLE
    }

    private fun closeLoadNotFound() {
        val pbSearch = view?.findViewById<ProgressBar>(R.id.pb_search)
        val img = view?.findViewById<ImageView>(R.id.no_search_img)
        val title = view?.findViewById<TextView>(R.id.tv_no_search_title)
        val subtitle = view?.findViewById<TextView>(R.id.tv_no_search_subtitle)

        pbSearch?.visibility = View.GONE
        img?.visibility = View.VISIBLE
        title?.visibility = View.VISIBLE
        subtitle?.visibility = View.VISIBLE
    }

    private fun closeLoadNoInternet() {
        val pbSearch = view?.findViewById<ProgressBar>(R.id.pb_search)
        val img = view?.findViewById<ImageView>(R.id.no_internet_search_img)
        val title = view?.findViewById<TextView>(R.id.tv_no_internet_search_title)
        val subtitle = view?.findViewById<TextView>(R.id.tv_no_internet_search_subtitle)

        pbSearch?.visibility = View.GONE
        img?.visibility = View.VISIBLE
        title?.visibility = View.VISIBLE
        subtitle?.visibility = View.VISIBLE
    }

    private fun closeAll() {
        val searchImg = view?.findViewById<ImageView>(R.id.search_img)
        val tvSearchTitle = view?.findViewById<TextView>(R.id.tv_search_title)
        val tvSearchSubtitle = view?.findViewById<TextView>(R.id.tv_search_subtitle)
        val pbSearch = view?.findViewById<ProgressBar>(R.id.pb_search)
        val searchResult = view?.findViewById<TextView>(R.id.search_result)
        val rvSearchList = view?.findViewById<RecyclerView>(R.id.rv_search_list)
        val imgNotFound = view?.findViewById<ImageView>(R.id.no_search_img)
        val titleNotFound = view?.findViewById<TextView>(R.id.tv_no_search_title)
        val subtitleNotFound = view?.findViewById<TextView>(R.id.tv_no_search_subtitle)
        val imgNoInternet = view?.findViewById<ImageView>(R.id.no_internet_search_img)
        val titleNoInternet = view?.findViewById<TextView>(R.id.tv_no_internet_search_title)
        val subtitleNoInternet = view?.findViewById<TextView>(R.id.tv_no_internet_search_subtitle)

        searchImg?.visibility = View.GONE
        tvSearchTitle?.visibility = View.GONE
        tvSearchSubtitle?.visibility = View.GONE
        pbSearch?.visibility = View.GONE
        searchResult?.visibility = View.GONE
        rvSearchList?.visibility = View.GONE
        imgNotFound?.visibility = View.GONE
        titleNotFound?.visibility = View.GONE
        subtitleNotFound?.visibility = View.GONE
        imgNoInternet?.visibility = View.GONE
        titleNoInternet?.visibility = View.GONE
        subtitleNoInternet?.visibility = View.GONE
    }
    
    private fun searching(value : String) {
        rv_search_list.layoutManager = LinearLayoutManager(activity)

        RetrofitClient.instances.searchMovies("5ffa6202", value).enqueue(object : Callback<SearchResultModel> {
            override fun onFailure(call: Call<SearchResultModel>, t: Throwable) {
                closeAll()
                list.clear()
                closeLoadNoInternet()
                Toast.makeText(activity, "No internet connection", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<SearchResultModel>,
                response: Response<SearchResultModel>
            ) {
                closeAll()
                val res = response.body()!!.Response.toBoolean()
                list.clear()
                if (res) {
                    Toast.makeText(activity, "Sukses", Toast.LENGTH_SHORT).show()
                    closeLoadSuccess()
                    val search = response.body()!!.Search
                    list.addAll(search)
                    searchResultAdapter = SearchResultAdapter(list)
                    rv_search_list.adapter = searchResultAdapter
                }else {
                    closeLoadNotFound()
                    Toast.makeText(activity, "Movie not found", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

}