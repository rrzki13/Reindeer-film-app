package com.farazrizki13.tutorapp1.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farazrizki13.tutorapp1.R
import com.farazrizki13.tutorapp1.`interface`.NavigationHost
import com.farazrizki13.tutorapp1.api.RetrofitClient
import com.farazrizki13.tutorapp1.model.MovieDetail
import com.luseen.spacenavigation.SpaceNavigationView
import kotlinx.android.synthetic.main.deskripsion_main.view.*
import kotlinx.android.synthetic.main.film_deskripsion.*
import kotlinx.android.synthetic.main.film_deskripsion.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmDeskripsionFragment : Fragment() {

    private lateinit var movieTitle : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.film_deskripsion, container, false)
        val value = arguments?.getString("code")
        val space : SpaceNavigationView = activity!!.findViewById(R.id.space)
        space.visibility = View.GONE

        if (value != null) {
            RetrofitClient.instances.getMovieDetail("5ffa6202", value)
                .enqueue(object : Callback<MovieDetail>{
                    override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                        closeAll()
                        Toast.makeText(activity, "No Internet Access", Toast.LENGTH_LONG).show()
                        showNoInternet()
                        view.back_movie_detail.visibility = View.GONE
                        view.pb_movie_detail.visibility = View.GONE

                        movieTitle = "noFilm"
                    }

                    override fun onResponse(
                        call: Call<MovieDetail>,
                        response: Response<MovieDetail>
                    ) {
                        view.back_movie_detail.visibility = View.GONE
                        view.pb_movie_detail.visibility = View.GONE

                        if (response.body()?.Response!!.toBoolean()) {
                            val title = response.body()?.Title
                            val year = response.body()?.Year
                            val img = response.body()?.Poster
                            val release = response.body()?.Released
                            val runtime = response.body()?.Runtime
                            val genre = response.body()?.Genre
                            val director = response.body()?.Director
                            val actors = response.body()?.Actors
                            val writer = response.body()?.Writer
                            val plot = response.body()?.Plot

                            val allTitle = "$title ($year)"

                            val requestOption = RequestOptions()
                                .placeholder(R.drawable.ic_launcher_background)
                                .error(R.drawable.ic_launcher_background)

                            Glide.with(activity!!)
                                .applyDefaultRequestOptions(requestOption)
                                .load(img)
                                .into(view.img_poster)

                            view.txt_title.text = allTitle
                            view.txt_release.text = release
                            view.txt_runtime.text = runtime
                            view.txt_director.text = director
                            view.txt_actors.text = actors
                            view.txt_writer.text = writer
                            view.txt_plot.text = plot
                            view.txt_genre.text = genre

                            movieTitle = title.toString()
                        }
                    }
                })
        }

        view.swl_deskripsion.setOnRefreshListener {
            swl_deskripsion.isRefreshing = true
            if (value != null) {
                view.back_movie_detail.visibility = View.VISIBLE
                view.pb_movie_detail.visibility = View.VISIBLE

                RetrofitClient.instances.getMovieDetail("5ffa6202", value)
                    .enqueue(object : Callback<MovieDetail>{
                        override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                            closeAll()
                            Toast.makeText(activity, "No Internet Access", Toast.LENGTH_LONG).show()
                            showNoInternet()
                            view.back_movie_detail.visibility = View.GONE
                            view.pb_movie_detail.visibility = View.GONE

                            movieTitle = "noFilm"
                        }

                        override fun onResponse(
                            call: Call<MovieDetail>,
                            response: Response<MovieDetail>
                        ) {
                            view.back_movie_detail.visibility = View.GONE
                            view.pb_movie_detail.visibility = View.GONE
                            closeAll()
                            showSuccess()
                            if (response.body()?.Response!!.toBoolean()) {
                                val title = response.body()?.Title
                                val year = response.body()?.Year
                                val img = response.body()?.Poster
                                val release = response.body()?.Released
                                val runtime = response.body()?.Runtime
                                val genre = response.body()?.Genre
                                val director = response.body()?.Director
                                val actors = response.body()?.Actors
                                val writer = response.body()?.Writer
                                val plot = response.body()?.Plot

                                val allTitle = "$title ($year)"

                                val requestOption = RequestOptions()
                                    .placeholder(R.drawable.ic_launcher_background)
                                    .error(R.drawable.ic_launcher_background)

                                Glide.with(activity!!)
                                    .applyDefaultRequestOptions(requestOption)
                                    .load(img)
                                    .into(view.img_poster)

                                view.txt_title.text = allTitle
                                view.txt_release.text = release
                                view.txt_runtime.text = runtime
                                view.txt_director.text = director
                                view.txt_actors.text = actors
                                view.txt_writer.text = writer
                                view.txt_plot.text = plot
                                view.txt_genre.text = genre

                                movieTitle = title.toString()
                            }
                        }
                    })
            }
            swl_deskripsion.isRefreshing = false
        }

        view.btn_to_trailer.setOnClickListener {
            val splitIt = movieTitle.split(" ").toTypedArray()
            var theTitle = ""
            splitIt.forEachIndexed { i, a ->
                run {
                    theTitle += when {
                        a == "&" -> "And+"
                        i < splitIt.size - 1 -> "$a+"
                        else -> a
                    }
                }
            }
            val url = "https://www.youtube.com/results?search_query=$theTitle+trailer"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            (activity as NavigationHost).moveActivity(i)
        }

        return  view
    }

    private fun closeAll() {
        val container = view?.findViewById<NestedScrollView>(R.id.container_deskripsion_main)
        val imgNoInternet = view?.findViewById<ImageView>(R.id.no_internet_img)
        val titleNoInternet = view?.findViewById<TextView>(R.id.tv_no_internet_title)
        val subTitleNoInternet = view?.findViewById<TextView>(R.id.tv_no_internet_subtitle)

        container?.visibility = View.GONE
        imgNoInternet?.visibility = View.GONE
        titleNoInternet?.visibility = View.GONE
        subTitleNoInternet?.visibility = View.GONE
    }

    private fun showNoInternet() {
        val imgNoInternet = view?.findViewById<ImageView>(R.id.no_internet_img)
        val titleNoInternet = view?.findViewById<TextView>(R.id.tv_no_internet_title)
        val subTitleNoInternet = view?.findViewById<TextView>(R.id.tv_no_internet_subtitle)

        imgNoInternet?.visibility = View.VISIBLE
        titleNoInternet?.visibility = View.VISIBLE
        subTitleNoInternet?.visibility = View.VISIBLE
    }

    private fun showSuccess() {
        val container = view?.findViewById<NestedScrollView>(R.id.container_deskripsion_main)
        container?.visibility = View.VISIBLE
    }

}