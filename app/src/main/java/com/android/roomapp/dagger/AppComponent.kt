package com.android.roomapp.dagger

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

const val APP_CTX = "app context"

@Singleton
@Component(modules = [DatabaseModule::class])
interface AppComponent {

    fun getViewComponentFactory(): ViewComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @Named(APP_CTX) ctx: Context): AppComponent
    }
}