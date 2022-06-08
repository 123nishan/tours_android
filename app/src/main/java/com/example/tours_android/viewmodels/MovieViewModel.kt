package com.example.tours_android.viewmodels


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tours_android.model.Movie
import com.example.tours_android.model.api.ApiHelperImpl
import com.example.tours_android.model.api.RetrofitBuilder
import com.example.tours_android.service.MovieService
import com.example.tours_android.service.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.create
import kotlin.math.log


class MovieViewModel:ViewModel(){
    val movies=MutableLiveData<List<Movie>?>()
    val responseCode=MutableLiveData<Int>()
    val errorMessage=MutableLiveData<String>()


//    fun getAllMovies(){
//
//        val response=repository.getAllMovies()
//        response.enqueue(object:Callback<List<Movie>>{
//
//
//            override fun onResponse(call: Call<List<Movie>>, response: retrofit2.Response<List<Movie>>) {
//                if(response.isSuccessful){
//                    movies.value=response.body()
//                    responseCode.value=response.code()
//                }
//                else{
//                    errorMessage.value=response.message()
//                    responseCode.value=response.code()
//                }
//
//            }
//
//
//            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
//                Log.d("response is failure",t.message.toString())
//                errorMessage.postValue(t.message)
//            }
//
//        })
//    }

    fun fetchMovies(){
        val apiHelper=ApiHelperImpl(RetrofitBuilder.apiInterface)
        apiHelper.getMovie()
            .enqueue(object:Callback<List<Movie>>{
                override fun onResponse(call: Call<List<Movie>>, response: retrofit2.Response<List<Movie>>) {
                    if(response.isSuccessful){
                        movies.postValue(response.body())
                        responseCode.value=response.code()
                    }
                    else{
                        errorMessage.value=response.message()
                        responseCode.value=response.code()
                    }

                }

                override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                    Log.d("response is failure",t.message.toString())
                    errorMessage.postValue(t.message)
                }

            })
    }
    fun getMoviesDetail():LiveData<List<Movie>?>{
        return movies

    }
}