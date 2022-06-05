package com.example.tours_android.service

class Repository constructor(private val movieService: MovieService){
    fun getAllMovies() = movieService.getAllMovies()
}