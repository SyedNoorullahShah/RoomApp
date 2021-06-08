package com.android.roomapp.ui.list_filter

import android.app.Application
import androidx.lifecycle.*
import com.android.roomapp.database.PersonEntity
import com.android.roomapp.repo.Repository
import kotlinx.coroutines.launch

/**
A [ViewModel] required for storing persons list for [ListFilterFragment] and for performing basic database operations on its behalf.
 */
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