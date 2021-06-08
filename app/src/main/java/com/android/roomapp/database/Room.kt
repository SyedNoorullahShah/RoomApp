package com.android.roomapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
[Dao] interface definition which shows all the database operations performed by our app.
Methods will be implemented automatically.
 */

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

    @Query("SELECT * FROM persons_table WHERE name LIKE :name")
    fun getPersonsByName(name: String): LiveData<List<PersonEntity>>

}


/**
An abstract [RoomDatabase] class which will be implemented automatically.
We'll be able to use [PersonDao] instance for performing database operations.
 */

@Database(entities = [PersonEntity::class], version = 1, exportSchema = false)
abstract class PersonDatabase : RoomDatabase() {
    abstract val personDao: PersonDao
}