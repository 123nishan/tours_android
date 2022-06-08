package com.example.tours_android.model.api


import com.example.tours_android.model.Movie
import retrofit2.Call

interface ApiHelper {
    fun getMovie():Call<List<Movie>>
}