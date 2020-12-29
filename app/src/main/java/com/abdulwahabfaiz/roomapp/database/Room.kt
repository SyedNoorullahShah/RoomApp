package com.abdulwahabfaiz.roomapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonDao {
    @Insert
    suspend fun insert(person: PersonEntity)

    @Query("UPDATE persons_table SET name=:name WHERE id=:id")
    suspend fun update(name: String, id: Int)

    @Delete
    suspend fun delete(person: PersonEntity)

    @Query("SELECT * FROM persons_table")
    fun getPersons(): LiveData<List<PersonEntity>>

    @Query("SELECT * FROM persons_table WHERE name=:name")
     fun getPersonsByName(name: String): Boolean
}

@Database(entities = [PersonEntity::class], version = 1, exportSchema = false)
abstract class PersonDatabase : RoomDatabase() {
    abstract val personDao: PersonDao

    companion object {
        private lateinit var INSTANCE: PersonDatabase

        fun getInstance(context: Context): PersonDatabase {
            synchronized(this) {
                if (!Companion::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PersonDatabase::class.java,
                        "persons"
                    ).fallbackToDestructiveMigration().build()
                }
            }

            return INSTANCE
        }
    }

}