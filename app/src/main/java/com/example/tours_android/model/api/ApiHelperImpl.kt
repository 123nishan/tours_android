package com.example.tours_android.model.api

import com.example.tours_android.model.Movie
import retrofit2.Call

class ApiHelperImpl (private val apiInterface: ApiInterface):ApiHelper {
    override fun getMovie(): Call<List<Movie>> = apiInterface.getMovie()

}