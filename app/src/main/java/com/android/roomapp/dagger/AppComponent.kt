package com.android.roomapp.dagger

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

const val APP_CTX = "app context"

/**
Application wide (parent) component responsible for:-
1. managing application wide dependencies (i.e 'Repository' and 'PersonDatabase's DAO instance')
2. returning [FragmentComponent.Factory] instance
 */
@Singleton
@Component(modules = [DatabaseModule::class])
interface AppComponent {

    fun getFragmentComponentFactory(): FragmentComponent.Factory

    /**
    Our own [Factory] interface definition so that we can bind the application context.
     */
    @Component.Factory
    interface Factory {
        //binding application context for future use
        fun create(@BindsInstance @Named(APP_CTX) ctx: Context): AppComponent
    }
}