package com.android.roomapp.ui.db_filter

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.android.roomapp.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
A [ViewModel] required for performing search operations directly on the Database level and storing the result list for [DatabaseFilterFragment].
 */
@HiltViewModel
class DatabaseFilterViewModel @Inject constructor(repo: Repository) : ViewModel() {
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