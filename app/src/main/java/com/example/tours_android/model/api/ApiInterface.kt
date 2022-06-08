package com.example.tours_android.model.api

import com.example.tours_android.model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("movielist.json")
    fun getMovie(): Call<List<Movie>>
}