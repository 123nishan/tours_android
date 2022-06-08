package com.example.tours_android.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.tours_android.R
import com.example.tours_android.model.Movie
import com.squareup.picasso.Picasso

class TourActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#008577")))

        supportActionBar!!.title = "Movie Detail"
        val myIntent=intent
        val movie=myIntent.getSerializableExtra("movie") as Movie

        val titleTextView:TextView= findViewById(R.id.tour_title_tv)
        val descriptionTextView:TextView= findViewById(R.id.tour_description_tv)
        val imageView:ImageView= findViewById(R.id.tour_image)
        titleTextView.text=movie.name
        descriptionTextView.text=movie.description

        Picasso.get().load(movie.imageUrl).fit().into(imageView)

//        val id: Int = intent.getIntExtra("position",0)
//        Toast.makeText(this, "Clicked $id", Toast.LENGTH_SHORT).show()
    }
}