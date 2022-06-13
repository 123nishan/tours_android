package com.example.tours_android.service

import com.example.tours_android.model.Movie
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
//TODO remove this file
interface MovieService {


    @GET("movielist.json")
    fun getAllMovies(): Call<List<Movie>>

    companion object{
        var service: MovieService?=null
       var  okhttp:OkHttpClient= OkHttpClient.Builder()
           .connectTimeout(15, TimeUnit.SECONDS)
           .writeTimeout(15, TimeUnit.SECONDS)
              .readTimeout(15, TimeUnit.SECONDS)
           .build()
        fun getInstance(): MovieService {
//            return Retrofit.Builder()
//                .baseUrl("https://howtodoandroid.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(MovieService::class.java)
            if (service == null) {
                val retrofit = retrofit2.Retrofit.Builder()
                    .client(okhttp)
                    .baseUrl("https://howtodoandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                service = retrofit.create(MovieService::class.java)
            }
    return service!!
        }
    }


}