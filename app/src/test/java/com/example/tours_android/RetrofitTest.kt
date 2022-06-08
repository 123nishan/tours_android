package com.example.tours_android

import android.app.Activity
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tours_android.model.Movie
import com.example.tours_android.model.api.ApiHelper
import com.example.tours_android.model.api.ApiHelperImpl
import com.example.tours_android.model.api.RetrofitBuilder
import com.example.tours_android.service.MovieService
import com.example.tours_android.service.Repository
import com.example.tours_android.viewmodels.MovieViewModel

import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock

import org.mockito.MockitoAnnotations
@RunWith(MockitoJUnitRunner::class)
class RetrofitTest {

    //https://stackoverflow.com/questions/58057769/method-getmainlooper-in-android-os-looper-not-mocked-still-occuring-even-after-a
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: MovieViewModel
    lateinit var apiHelper: ApiHelper
    @Mock
    lateinit var movieObserver: Observer<List<Movie>?>

    lateinit var mockWebServer: MockWebServer
    @Before
    fun setup(){

   // MockitoAnnotations.initMocks(this)
        //val movieService = MovieService.getInstance()
        viewModel=MovieViewModel()
        viewModel.getMoviesDetail().observeForever (movieObserver)

        //viewModel=ViewModelProviders.of

//        mockWebServer= MockWebServer()
//        mockWebServer.start()
        apiHelper=ApiHelperImpl(RetrofitBuilder.apiInterface)
//        apiHelper=Repository(remoteApi)
    }
    @Test
    fun `fetch details and check response code 200 returned`() {

      val response=MockResponse()
        response.setResponseCode(200)
       val actualResponse=apiHelper.getMovie().execute()

        //System.out.println("RetrofitTest actualCode"+actualCode)
        assertEquals(response.toString().contains("200"),actualResponse.code().toString())


    }


}