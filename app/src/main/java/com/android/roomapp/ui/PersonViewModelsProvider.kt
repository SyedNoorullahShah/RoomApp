package com.android.roomapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.roomapp.repo.Repository
import com.android.roomapp.ui.db_filter.DatabaseFilterViewModel
import com.android.roomapp.ui.list_filter.ListFilterViewModel

class PersonViewModelsProvider(
    private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(ListFilterViewModel::class.java) -> {
                ListFilterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DatabaseFilterViewModel::class.java) -> {
                DatabaseFilterViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
}