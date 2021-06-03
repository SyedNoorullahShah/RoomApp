package com.android.roomapp.ui.list_filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.roomapp.dagger.ViewModelScope
import com.android.roomapp.database.PersonEntity
import com.android.roomapp.repo.Repository
import kotlinx.coroutines.launch

@ViewModelScope
class ListFilterViewModel(private val repo: Repository) : ViewModel() {

    val personsList = repo.getPersons()

    fun addPerson(person: PersonEntity) {
        viewModelScope.launch {
            repo.addPerson(person)
        }
    }

    fun delete(person: PersonEntity) {
        viewModelScope.launch {
            repo.deletePerson(person)
        }
    }

    fun update(name: String, id: Int) {
        viewModelScope.launch {
            repo.updatePerson(name, id)
        }
    }

}