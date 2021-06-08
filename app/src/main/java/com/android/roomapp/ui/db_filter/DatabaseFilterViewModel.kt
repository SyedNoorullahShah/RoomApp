package com.android.roomapp.ui.db_filter

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.android.roomapp.dagger.FragmentScope
import com.android.roomapp.repo.Repository
import com.android.roomapp.ui.list_filter.ListFilterFragment

/**
A [ViewModel] required for performing search operations directly on the Database level and storing the result list for [DatabaseFilterFragment].
 */
@FragmentScope
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