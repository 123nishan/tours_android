package com.example.tours_android

data class Recyclerlist(val items:ArrayList<RecyclerData>)
data class RecyclerData(val name:String,val description:String,val tourNumber:Int,val imageUrl:String)