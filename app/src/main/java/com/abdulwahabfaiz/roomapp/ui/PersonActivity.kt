package com.abdulwahabfaiz.roomapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdulwahabfaiz.roomapp.databinding.ActivityPersonBinding

class PersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityPersonBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

    }
}