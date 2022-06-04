package com.example.tours_android.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tours_android.Recyclerlist
import okhttp3.*

class RecyclerActivityViewModel:ViewModel() {
    lateinit var recyclerListData:MutableLiveData<Recyclerlist>
    init {
        recyclerListData=MutableLiveData()
    }
    fun getRecyclerListDataObservable():MutableLiveData<Recyclerlist>{
        return recyclerListData
    }

    fun makeApiCall(){
        var client: OkHttpClient = OkHttpClient()
        var url:String="https://reqres.in/api/users?page=2"

        var request: Request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {

                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {

                if(response.isSuccessful){
//                    var strResponse:String = response.body()?.string()!!
//                    println("response is $strResponse")
                    //update textview with response
                   // this@MainActivity.runOnUiThread(java.lang.Runnable{mTextViewResult?.text = strResponse})

                    // mTextViewResult?.text = strResponse
                }
            }

        })
    }
}