package com.example.android.clippingexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO 1.1
        /*setContentView(R.layout.activity_main)*/
        setContentView(ClippedView(this))
    }
}