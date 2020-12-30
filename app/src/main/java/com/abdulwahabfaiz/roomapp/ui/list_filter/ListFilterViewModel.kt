package com.abdulwahabfaiz.roomapp.ui.list_filter

import android.app.Application
import androidx.lifecycle.*
import com.abdulwahabfaiz.roomapp.database.PersonEntity
import com.abdulwahabfaiz.roomapp.repo.Repository
import kotlinx.coroutines.launch

class ListFilterViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: Repository = Repository.getInstance(application.applicationContext)

    val personsList = repo.getPersons()

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