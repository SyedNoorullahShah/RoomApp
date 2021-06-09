package com.android.roomapp.ui.list_filter

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.roomapp.R
import com.android.roomapp.adapters.OnItemClickListener
import com.android.roomapp.adapters.PersonAdapter
import com.android.roomapp.database.PersonEntity
import com.android.roomapp.databinding.ListFilterFragmentBinding
import com.android.roomapp.dialogs.AddUpdateDialog
import com.android.roomapp.dialogs.AddUpdatePersonListener
import com.android.roomapp.helpers.Actions
import dagger.hilt.android.AndroidEntryPoint

/**
This is the screen where we will be able to filter results FROM RECYCLER VIEW'S ARRAY LIST.
It means whenever the user searches for a name, the filter operation will be performed ON THE ADAPTER'S DATA and the generated result will be returned via LiveData
Moreover we will be able to add,edit and remove data as well.
 */
@AndroidEntryPoint
class ListFilterFragment : Fragment(), AddUpdatePersonListener, OnItemClickListener {
    private lateinit var listFilterFragmentBinding: ListFilterFragmentBinding
    private lateinit var personAdapter: PersonAdapter
    private val viewModel by viewModels<ListFilterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listFilterFragmentBinding = ListFilterFragmentBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        viewModel.personsList.observe(viewLifecycleOwner) { personsList ->
            personAdapter.fullList = personsList
            personAdapter.filter.filter(listFilterFragmentBinding.personFinder.query.toString())
        }
        setUiComponents()

        return listFilterFragmentBinding.root
    }


    private fun setUiComponents() {
        setRecyclerview()
        setSearchViewListener()
        listFilterFragmentBinding.fabBtnAdd.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("ACTION", Actions.ACTION_ADD.name)
            startDialog(bundle)
        }

    }


    private fun setRecyclerview() {
        personAdapter = PersonAdapter(this, layoutInflater)
        listFilterFragmentBinding.personList.layoutManager = LinearLayoutManager(context)
        listFilterFragmentBinding.personList.adapter = personAdapter
    }


    private fun setSearchViewListener() {
        listFilterFragmentBinding.personFinder.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                personAdapter.filter.filter(newText)
                return false
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    override fun addUpdatePerson(a: Actions, name: String, id: Int) {
        if (a.name == Actions.ACTION_ADD.name) {
            viewModel.addPerson(PersonEntity(name))
        } else {
            viewModel.update(name, id)
        }
    }

    override fun onItemClick(action: Actions, person: PersonEntity) {
        if (action.name == Actions.ACTION_REMOVE.name) {
            viewModel.delete(person)
        } else {
            val bundle = Bundle()
            bundle.putString("ACTION", Actions.ACTION_UPDATE.name)
            bundle.putInt("PERSON_ID", person.id)
            bundle.putString("PERSON_NAME", person.name)
            startDialog(bundle)
        }
    }

    private fun startDialog(bundle: Bundle) {
        val dialog = AddUpdateDialog().apply {
            arguments = bundle
        }
        dialog.setTargetFragment(this, 0)
        dialog.show(parentFragmentManager, "PERSON_DIALOG")
    }

}