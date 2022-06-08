package com.example.tours_android.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(val name:String,val imageUrl:String,val category:String, @SerializedName("desc") val description:String):Serializable

