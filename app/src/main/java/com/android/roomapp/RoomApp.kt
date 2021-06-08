package com.android.roomapp

import android.app.Application
import com.android.roomapp.dagger.AppComponent
import com.android.roomapp.dagger.DaggerAppComponent

class RoomApp : Application() {
    lateinit var appComponent: AppComponent
        private set

    /**
    Instantiating top most component of our dependency graph (i.e [AppComponent]) and storing it for further use.
     */
    override fun onCreate() {
        appComponent = DaggerAppComponent.factory().create(this)
        super.onCreate()
    }
}