package com.android.roomapp.dagger

import androidx.fragment.app.Fragment
import com.android.roomapp.ui.db_filter.DatabaseFilterFragment
import com.android.roomapp.ui.list_filter.ListFilterFragment
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Named

const val LIFECYCLE_OWNER = "lifecycle owner"

/**
Fragment wide (child) component responsible for:-
1. storing and managing Fragment wide dependencies (i.e 'DatabaseFilterViewModel' and 'ListFilterViewModel')
2. injecting the dependencies into their respective fragments.
 */

@FragmentScope
@Subcomponent(modules = [ViewModelModule::class])
interface FragmentComponent {
    fun inject(fragment: ListFilterFragment)
    fun inject(fragment: DatabaseFilterFragment)

    /**
    Our own [Factory] interface definition so that we can bind the fragment instance (that acts as the lifecycle owner for creating view models used in [ViewModelModule]).
     */
    @Subcomponent.Factory
    interface Factory {
        //binding fragment instance
        fun create(@BindsInstance @Named(LIFECYCLE_OWNER) owner: Fragment): FragmentComponent
    }
}