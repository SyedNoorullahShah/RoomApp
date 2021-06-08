package com.android.roomapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.roomapp.databinding.ActivityPersonBinding

/**
Host activity for both the fragments (i.e ListFilterFragment and DatabaseFilterFragment).
 */
class PersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityPersonBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

    }
}