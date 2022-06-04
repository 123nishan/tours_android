package com.example.tours_android

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class FavActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#008577")))

        supportActionBar!!.title = "Tours"

        var actionBar=getSupportActionBar()
        //display back button
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)

        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}