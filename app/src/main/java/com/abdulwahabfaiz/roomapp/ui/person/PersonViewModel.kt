package com.abdulwahabfaiz.roomapp.ui.person

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.abdulwahabfaiz.roomapp.database.PersonEntity
import com.abdulwahabfaiz.roomapp.repo.Repository
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: Repository = Repository.getInstance(application.applicationContext)
    private val dbPersonsList: LiveData<List<PersonEntity>> = repo.getPersons()
    val personsList = MediatorLiveData<List<PersonEntity>>()
    private lateinit var persons: List<PersonEntity>
    private var isFilterOn: Boolean = false

    init {
        personsList.addSource(dbPersonsList) {
            it?.let {
                if (!isFilterOn) {
                    personsList.value = it
                    persons = it
                }
            }
        }
    }

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

    fun getPersonsByName(name: String?) {
        if (name.isNullOrEmpty()) {
            resetList()
            return
        }

        dbPersonsList.value?.let {
            personsList.value = getListByName(name, it)
        }
    }

    private fun getListByName(name: String, list: List<PersonEntity>): List<PersonEntity>? {
        isFilterOn = true
        return list.filter {
            it.name.contains(name)
        }
    }

    private fun resetList() {
        isFilterOn = false
        personsList.value = persons
    }

    fun update(name: String, id: Int) {
        viewModelScope.launch {
            repo.update(name, id)
        }
    }
}