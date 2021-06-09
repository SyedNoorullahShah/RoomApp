package com.android.roomapp.hilt

import android.content.Context
import androidx.room.Room
import com.android.roomapp.database.PersonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
A [Module] responsible for creating and providing [PersonDatabase.personDao] instance.
It will also provide [PersonDatabase] (singleton) instance for providing the Dao instance.
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun database(@ApplicationContext appCtx: Context) =
        Room.databaseBuilder(
            appCtx.applicationContext,
            PersonDatabase::class.java,
            "persons"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun getDatabaseDaoInstance(database: PersonDatabase) = database.personDao
}