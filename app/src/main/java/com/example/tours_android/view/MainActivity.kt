package com.example.tours_android.view

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.tours_android.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
   var mTextViewResult:TextView? = null
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        mTextViewResult = findViewById(R.id.textViewResult)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#008577")))

        supportActionBar!!.title = "Tours"


        val navHostFragment=supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        navController=navHostFragment.navController
        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setupWithNavController(bottomNavigationView,navController)

//        val exampleList=generateDummyList(10)
//        recycler_view


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu,menu)
        return true
    }

   override fun onOptionsItemSelected(item: MenuItem)= when(item.itemId){
            R.id.action_fav ->{
                    val intent=Intent(this, FavActivity::class.java)
                    startActivity(intent)
//                    return true
                true
            }
            else->{
                //the action was recognized but not handled
                super.onOptionsItemSelected(item)
            }
        }

}