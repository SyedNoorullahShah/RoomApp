package com.android.roomapp

import android.app.Application
import com.android.roomapp.dagger.AppComponent
import com.android.roomapp.dagger.DaggerAppComponent

class RoomApp : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        appComponent = DaggerAppComponent.factory().create(this)
        super.onCreate()
    }
}