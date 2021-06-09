package com.android.roomapp.repo

import androidx.lifecycle.LiveData
import com.android.roomapp.database.PersonDao
import com.android.roomapp.database.PersonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
Simple repository (singleton) class which acts as a medium between both the viewmodels and the data source (i.e our PersonDatabase)
 */
@Singleton
class Repository @Inject constructor(private val personDao: PersonDao) {

    fun getPersons() = personDao.getPersons()

    fun getPersonsByName(name: String): LiveData<List<PersonEntity>> {
        val search = "%$name%"
        return personDao.getPersonsByName(search)
    }

    suspend fun addPerson(person: PersonEntity) {
        withContext(Dispatchers.IO) {
            personDao.insert(person)
        }
    }

    suspend fun delete(person: PersonEntity) {
        withContext(Dispatchers.IO) {
            personDao.delete(person)
        }
    }

    suspend fun update(name: String, id: Int) {
        withContext(Dispatchers.IO) {
            personDao.update(name, id)
        }
    }
}

