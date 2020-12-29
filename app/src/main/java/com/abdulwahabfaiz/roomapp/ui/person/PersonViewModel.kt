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
    val personsList: LiveData<List<PersonEntity>> = repo.getPersons()

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

/*
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
*/

    fun update(name: String, id: Int) {
        viewModelScope.launch {
            repo.update(name, id)
        }
    }
}