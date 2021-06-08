package com.android.roomapp.dagger

import android.content.Context
import androidx.room.Room
import com.android.roomapp.database.PersonDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
A [Module] responsible for creating and providing [PersonDatabase.personDao] instance.
It will also provide [PersonDatabase] (singleton) instance for providing the Dao instance.
 */

@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun database(@Named(APP_CTX) appCtx: Context) =
        Room.databaseBuilder(
            appCtx.applicationContext,
            PersonDatabase::class.java,
            "persons"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun getDatabaseDaoInstance(database: PersonDatabase) = database.personDao
}
