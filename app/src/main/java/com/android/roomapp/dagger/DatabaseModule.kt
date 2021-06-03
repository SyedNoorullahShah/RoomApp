package com.android.roomapp.dagger

import android.content.Context
import androidx.room.Room
import com.android.roomapp.database.PersonDao
import com.android.roomapp.database.PersonDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class DatabaseModule {
    companion object {
        @Provides
        @Singleton
        fun getDatabaseDaoInstance(@Named(APP_CTX) appCtx: Context): PersonDao =
            Room.databaseBuilder(
                appCtx.applicationContext,
                PersonDatabase::class.java,
                "persons"
            ).fallbackToDestructiveMigration().build().personDao
    }
}
