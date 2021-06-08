package com.android.roomapp.ui.db_filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.roomapp.RoomApp
import com.android.roomapp.adapters.PersonAdapter
import com.android.roomapp.dagger.AppComponent
import com.android.roomapp.databinding.DbFilterFragmentBinding
import com.android.roomapp.ui.PersonActivity
import javax.inject.Inject

/**
This is the screen where we will be able to filter results DIRECTLY FROM DATABASE.
It means whenever the user searches for a name, the filter operation will be performed ON THE DATABASE LEVEL and the generated result will be returned via LiveData
 */

class DatabaseFilterFragment : Fragment() {

    @Inject
    lateinit var viewmodel: DatabaseFilterViewModel
    private lateinit var dbFilterFragmentBinding: DbFilterFragmentBinding
    private lateinit var personAdapter: PersonAdapter

    /**
    Instantiating [FragmentComponent] and using it to inject dependency (i.e [DatabaseFilterViewModel]).
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        val app = (context as PersonActivity).application
        super.onCreate(savedInstanceState)
        (app as RoomApp).appComponent
            .getFragmentComponentFactory()
            .create(owner = this)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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