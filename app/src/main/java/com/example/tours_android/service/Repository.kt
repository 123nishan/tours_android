package com.example.tours_android.service
//TODO remove this file
class Repository constructor(private val movieService: MovieService){
    fun getAllMovies() = movieService.getAllMovies()
}