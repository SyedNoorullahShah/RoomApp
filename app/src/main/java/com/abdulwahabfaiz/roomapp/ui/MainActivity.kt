package com.abdulwahabfaiz.roomapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdulwahabfaiz.roomapp.R
import com.abdulwahabfaiz.roomapp.ui.adapters.OnItemClickListener
import com.abdulwahabfaiz.roomapp.ui.adapters.PersonAdapter
import com.abdulwahabfaiz.roomapp.database.PersonEntity
import com.abdulwahabfaiz.roomapp.databinding.ActivityMainBinding
import com.abdulwahabfaiz.roomapp.enums.Actions

class MainActivity : AppCompatActivity(), AddUpdatePersonListener, OnItemClickListener {
    private lateinit var viewmodel: PersonViewModel
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var personAdapter: PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setRecyclerview()
        setSearchViewListener()
        viewmodel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                PersonViewModel::class.java
            )

        viewmodel.personList.observe(this) { persons ->
            Toast.makeText(this,"here",Toast.LENGTH_SHORT).show()
            personAdapter.submitList(persons)
        }
    }

    private fun setSearchViewListener() {
        activityMainBinding.personFinder.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                var getUserEitherExistOrNot = viewmodel.getPersonsByName(query)
                Log.d("userExist", "onQueryTextSubmit: $getUserEitherExistOrNot")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }


    private fun setRecyclerview() {
        personAdapter = PersonAdapter(this, layoutInflater)
        activityMainBinding.personList.layoutManager = LinearLayoutManager(this)
        activityMainBinding.personList.adapter = personAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val bundle = Bundle()
        bundle.putString("ACTION", Actions.ACTION_ADD.name)
        startDialog(bundle)
        return true
    }

    override fun addUpdatePerson(a: Actions, name: String, id: Int) {
        if (a.name.equals(Actions.ACTION_ADD.name)) {
            viewmodel.addPerson(PersonEntity(name))
        } else {
            viewmodel.update(name, id)
        }
    }

    override fun onItemClick(action: Actions, person: PersonEntity) {
        if (action.name.equals(Actions.ACTION_REMOVE.name)) {
            viewmodel.delete(person)
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
        dialog.show(supportFragmentManager, "PERSON_DIALOG")
    }

}