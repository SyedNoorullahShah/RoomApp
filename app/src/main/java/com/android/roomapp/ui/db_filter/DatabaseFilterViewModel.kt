package com.android.roomapp.ui.db_filter

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.android.roomapp.dagger.ViewModelScope
import com.android.roomapp.repo.Repository

@ViewModelScope
class DatabaseFilterViewModel(private val repo: Repository) : ViewModel() {
    private val filterSearch = MutableLiveData("")
    val personsList = Transformations.switchMap(filterSearch) { filterQuery ->
        if (TextUtils.isEmpty(filterQuery)) {
            repo.getPersons()
        } else {
            repo.getPersonsByName(filterQuery)
        }
    }

    fun getPersonsByName(name: String?) {
        filterSearch.value = name
    }
}