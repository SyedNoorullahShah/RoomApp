package com.android.roomapp.ui.db_filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.roomapp.adapters.PersonAdapter
import com.android.roomapp.databinding.DbFilterFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

/**
This is the screen where we will be able to filter results DIRECTLY FROM DATABASE.
It means whenever the user searches for a name, the filter operation will be performed ON THE DATABASE LEVEL and the generated result will be returned via LiveData
 */

@AndroidEntryPoint
class DatabaseFilterFragment : Fragment() {
    private val viewModel by viewModels<DatabaseFilterViewModel>()
    private lateinit var dbFilterFragmentBinding: DbFilterFragmentBinding
    private lateinit var personAdapter: PersonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dbFilterFragmentBinding = DbFilterFragmentBinding.inflate(inflater, container, false)
        viewModel.personsList.observe(viewLifecycleOwner, { personsList ->
            personAdapter.submitList(personsList)
        })
        setSearchViewListener()
        setRecyclerView()
        return dbFilterFragmentBinding.root
    }

    private fun setSearchViewListener() {
        dbFilterFragmentBinding.dbSearchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(name: String): Boolean {
                viewModel.getPersonsByName(name)
                return false
            }

        })

    }

    private fun setRecyclerView() {
        personAdapter = PersonAdapter(null, layoutInflater)
        dbFilterFragmentBinding.dbPersonList.layoutManager = LinearLayoutManager(context)
        dbFilterFragmentBinding.dbPersonList.adapter = personAdapter
    }

}