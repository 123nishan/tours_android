package com.example.tours_android.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tours_android.service.Repository

//class MovieViewModelFactory (val repository: Repository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
//            MovieViewModel(repository) as T
//        } else {
//            throw IllegalArgumentException("Unknown ViewModel class")
//        }
//    }
//
//
//}