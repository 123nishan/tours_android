package com.example.tours_android.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tours_android.model.Movie
import com.example.tours_android.service.MovieService
import com.example.tours_android.service.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.create


class MovieViewModel (val repository: Repository):ViewModel(){
    val movies=MutableLiveData<List<Movie>>()
    val errorMessage=MutableLiveData<String>()


    fun getAllMovies(){

        val response=repository.getAllMovies()
        response.enqueue(object:Callback<List<Movie>>{


            override fun onResponse(call: Call<List<Movie>>, response: retrofit2.Response<List<Movie>>) {
                movies.postValue(response.body())
            }
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
}