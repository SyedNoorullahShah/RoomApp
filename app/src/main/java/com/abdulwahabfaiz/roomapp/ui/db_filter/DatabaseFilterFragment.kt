package com.abdulwahabfaiz.roomapp.ui.db_filter

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdulwahabfaiz.roomapp.adapters.PersonAdapter
import com.abdulwahabfaiz.roomapp.databinding.DbFilterFragmentBinding
import com.abdulwahabfaiz.roomapp.ui.PersonActivity

class DatabaseFilterFragment : Fragment() {
    private lateinit var personAdapter: PersonAdapter
    private lateinit var viewmodel: DatabaseFilterViewModel
    private lateinit var dbFilterFragmentBinding: DbFilterFragmentBinding
    private lateinit var application: Application

    override fun onAttach(context: Context) {
        super.onAttach(context)
        application = (context as PersonActivity).application
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                DatabaseFilterViewModel::class.java
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbFilterFragmentBinding = DbFilterFragmentBinding.inflate(inflater, container, false)
        viewmodel.personsList.observe(viewLifecycleOwner, { personsList ->
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
                viewmodel.getPersonsByName(name)
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