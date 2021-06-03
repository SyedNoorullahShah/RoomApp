package com.android.roomapp.dagger

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.roomapp.repo.Repository
import com.android.roomapp.ui.PersonViewModelsProvider
import com.android.roomapp.ui.db_filter.DatabaseFilterViewModel
import com.android.roomapp.ui.list_filter.ListFilterViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class ViewModelModule {
    companion object {
        @Provides
        @ViewModelScope
        fun provideListFilterViewModel(
            @Named(LIFECYCLE_OWNER) owner: Fragment,
            repository: Repository
        ) =
            ViewModelProvider(owner, PersonViewModelsProvider(repository)).get(
                ListFilterViewModel::class.java
            )

        @Provides
        @ViewModelScope
        fun provideDatabaseFilterViewModel(
            @Named(LIFECYCLE_OWNER) owner: Fragment,
            repository: Repository
        ) =
            ViewModelProvider(owner, PersonViewModelsProvider(repository)).get(
                DatabaseFilterViewModel::class.java
            )
    }
}