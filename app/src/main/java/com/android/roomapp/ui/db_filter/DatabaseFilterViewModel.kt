package com.android.roomapp.ui.db_filter

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.android.roomapp.repo.Repository

/**
A [ViewModel] required for performing search operations directly on the Database level and storing the result list for [DatabaseFilterFragment].
 */
class DatabaseFilterViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: Repository = Repository.getInstance(application.applicationContext)
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