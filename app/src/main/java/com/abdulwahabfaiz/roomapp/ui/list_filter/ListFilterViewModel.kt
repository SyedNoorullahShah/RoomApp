package com.abdulwahabfaiz.roomapp.ui.person

import android.app.Application
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.*
import com.abdulwahabfaiz.roomapp.database.PersonEntity
import com.abdulwahabfaiz.roomapp.repo.Repository
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: Repository = Repository.getInstance(application.applicationContext)

    val personsList = repo.getPersons()

    /*Transformations.switchMap(filterSearch) { filterQuery ->
        if (TextUtils.isEmpty(filterQuery)) {
            repo.getPersons()
        } else {
            repo.getPersonsByName(filterQuery)
        }
    }*/


    fun addPerson(person: PersonEntity) {
        viewModelScope.launch {
            repo.addPerson(person)
        }
    }

    fun delete(person: PersonEntity) {
        viewModelScope.launch {
            repo.delete(person)
        }
    }

    fun update(name: String, id: Int) {
        viewModelScope.launch {
            repo.update(name, id)
        }
    }

}