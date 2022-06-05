package com.example.tours_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tours_android.R

class TourActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour)
        val id: Int = intent.getIntExtra("position",0)
        Toast.makeText(this, "Clicked $id", Toast.LENGTH_SHORT).show()
    }
}