package com.abdulwahabfaiz.roomapp.repo

import android.app.Person
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abdulwahabfaiz.roomapp.database.PersonDao
import com.abdulwahabfaiz.roomapp.database.PersonDatabase
import com.abdulwahabfaiz.roomapp.database.PersonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository private constructor(private val personDao: PersonDao) {

    companion object {
        private var repository: Repository? = null

        fun getInstance(context: Context): Repository =
            repository
                ?: buildRepo(PersonDatabase.getInstance(context.applicationContext).personDao).also {
                    repository = it
                }

        private fun buildRepo(personDao: PersonDao): Repository = Repository(personDao)
    }

    fun getPersons() = personDao.getPersons()

    fun getPersonsByName(name: String):LiveData<List<PersonEntity>>{
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

