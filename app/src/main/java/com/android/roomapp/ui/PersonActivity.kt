package com.android.roomapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.roomapp.databinding.ActivityPersonBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Host activity for both the fragments (i.e ListFilterFragment and DatabaseFilterFragment).
 */


/**
 *[@AndroidEntryPoint] marks an Android component class to be setup for injection with the standard Hilt Dagger Android
 * components (please refer to https://dagger.dev/hilt/components).
 * Currently, this supports activities, fragments, views, services, and broadcast receivers.
 */
@AndroidEntryPoint
class PersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityPersonBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

    }
}