package com.example.movies_coroutine_mvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies_coroutine_mvvm.R

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.fragment_container,MoviesFragment.newInstance())
            ft.commit()
    }
}

