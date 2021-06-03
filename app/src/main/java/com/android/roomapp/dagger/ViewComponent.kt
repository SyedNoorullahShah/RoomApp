package com.android.roomapp.dagger

import androidx.fragment.app.Fragment
import com.android.roomapp.ui.db_filter.DatabaseFilterFragment
import com.android.roomapp.ui.list_filter.ListFilterFragment
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Named

const val LIFECYCLE_OWNER = "lifecycle owner"

@ViewModelScope
@Subcomponent(modules = [ViewModelModule::class])
interface ViewComponent {
    fun inject(fragment: ListFilterFragment)
    fun inject(fragment: DatabaseFilterFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance @Named(LIFECYCLE_OWNER) owner: Fragment): ViewComponent
    }
}