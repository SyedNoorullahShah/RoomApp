package com.abdulwahabfaiz.roomapp.ui

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.abdulwahabfaiz.roomapp.database.PersonDatabase
import com.abdulwahabfaiz.roomapp.database.PersonEntity
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {
    private val personDao = PersonDatabase.getInstance(application.applicationContext).personDao
    var personList: LiveData<List<PersonEntity>>

    var userExist: Boolean? = null


    init {
        personList = personDao.getPersons()
    }

    fun addPerson(person: PersonEntity) {
        viewModelScope.launch {
            personDao.insert(person)
        }
    }

    fun delete(person: PersonEntity) {
        viewModelScope.launch {
            personDao.delete(person)
        }
    }

     fun getPersonsByName(name: String?) {
        if (name.isNullOrEmpty()) {
            personList = personDao.getPersons()
            return
        }
        //personList = personDao.getPersonsByName(name)
      // viewModelScope.launch {
         var runnable = Runnable {
             userExist = personDao.getPersonsByName(name)
             Log.d("userexist", "getPersonsByName: $userExist")
         }
         AsyncTask.execute(runnable)


      //  }
    }

    fun update(name: String, id: Int) {
        viewModelScope.launch {
            personDao.update(name, id)
        }
    }
}