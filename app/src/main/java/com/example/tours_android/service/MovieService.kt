package com.example.tours_android.service

import com.example.tours_android.model.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MovieService {


    @GET("movielist.json")
    fun getAllMovies(): Call<List<Movie>>

    companion object{
        var service: MovieService?=null
        fun getInstance(): MovieService {
//            return Retrofit.Builder()
//                .baseUrl("https://howtodoandroid.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()

            if (service == null) {
                val retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                service = retrofit.create(MovieService::class.java)
            }
    return service!!
        }
    }


}