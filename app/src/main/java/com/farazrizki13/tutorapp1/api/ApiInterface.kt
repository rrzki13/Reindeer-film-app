package com.farazrizki13.tutorapp1.api

import com.farazrizki13.tutorapp1.model.MovieDetail
import com.farazrizki13.tutorapp1.model.SearchResultModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET(".")
    fun getMovieDetail(
        @Query("apikey") apikey : String,
        @Query("i") i : String
    ) : Call<MovieDetail>

    @GET(".")
    fun searchMovies(
        @Query("apikey") apikey: String,
        @Query("s") s : String
    ) : Call<SearchResultModel>

}