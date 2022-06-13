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
import junit.framework.Assert.assertNotNull
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
import org.junit.After
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock

import org.mockito.MockitoAnnotations
import java.net.HttpURLConnection

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

    MockitoAnnotations.initMocks(this)
        //val movieService = MovieService.getInstance()
        viewModel=MovieViewModel()
        viewModel.getMoviesDetail().observeForever (movieObserver)

        //viewModel=ViewModelProviders.of

        mockWebServer= MockWebServer()
        mockWebServer.start()
        apiHelper=ApiHelperImpl(RetrofitBuilder.apiInterface)
//        apiHelper=Repository(remoteApi)
    }
    @Test
    fun `read sample success json file`(){
        val reader = MockResponseFileReader("movielist.json")
        assertNotNull(reader.content)
    }

    @Test
    fun `fetch details and check response Code 200 returned`(){
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("movielist.json").content)
        mockWebServer.enqueue(response)
        // Act
        val  actualResponse = apiHelper.getMovie().execute()
        // Assert
        assertEquals(response.toString().contains("200"),actualResponse.code().toString().contains("200"))
    }

//    @Test
//    fun `fetch details for failed response 400 returned`(){
//        // Assign
//        val response = MockResponse()
//            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
//            .setBody(MockResponseFileReader("failed_response.json").content)
//        mockWebServer.enqueue(response)
//        // Act
//        val  actualResponse = apiHelper.getMovie().execute()
//        // Assert
//        print(actualResponse.toString())
//        assertEquals(response.toString().contains("400"),actualResponse.toString().contains("400"))
//    }
    /**
     * check if the first movie is the same as the one in the json file
     * for example, the first movie in the json file is "Coco"
     */
    @Test
fun `fetch details and check response success returned`(){
    // Assign
    val response = MockResponse()
        .setResponseCode(HttpURLConnection.HTTP_OK)
        .setBody(MockResponseFileReader("movielist.json").content)
    mockWebServer.enqueue(response)
    val mockResponse = response.getBody()?.readUtf8()
    // Act
    val  actualResponse = apiHelper.getMovie().execute()
    // Assert

    assertEquals(response.getBody()?.readUtf8()?.contains("Coco"), actualResponse.body()?.get(0)?.name.equals("Coco"))
}

@After
fun tearDown() {
    viewModel.getMoviesDetail().removeObserver(movieObserver)
    mockWebServer.shutdown()
}

}