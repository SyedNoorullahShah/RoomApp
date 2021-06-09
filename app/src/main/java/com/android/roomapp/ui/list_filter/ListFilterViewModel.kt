package com.android.roomapp.ui.list_filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.roomapp.database.PersonEntity
import com.android.roomapp.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
A [ViewModel] required for storing persons list for [ListFilterFragment] and for performing basic database operations on its behalf.
 */
@HiltViewModel      //please refer to the official doc to understand this annotation (https://dagger.dev/hilt/view-model)
class ListFilterViewModel @Inject constructor(private val repo: Repository) : ViewModel() {

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