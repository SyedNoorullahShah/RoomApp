package com.abdulwahabfaiz.roomapp.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.abdulwahabfaiz.roomapp.databinding.PersonDialogBinding
import com.abdulwahabfaiz.roomapp.helpers.Actions
import com.abdulwahabfaiz.roomapp.ui.list_filter.ListFilterFragment

interface AddUpdatePersonListener {
    fun addUpdatePerson(actions: Actions, name: String, int: Int)
}


class AddUpdateDialog : DialogFragment() {

    private lateinit var addUpdatePersonListener: AddUpdatePersonListener


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(context)
        val binding = PersonDialogBinding.inflate(layoutInflater)
        addUpdatePersonListener = targetFragment as ListFilterFragment
        dialogBuilder.setView(binding.root)
            .setTitle(getTitle())
            .setNegativeButton("cancel") { dialog, which -> }
            .setPositiveButton(
                "ok"
            ) { dialog, which ->
                val name = binding.personEditName.text.toString()

                if (name.isEmpty()) {
                    Toast.makeText(context, "Enter valid name", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    addUpdatePersonListener.addUpdatePerson(getAction(), name, requireArguments().getInt("PERSON_ID"))
                }
            }

        return dialogBuilder.create()
    }

    private fun getAction() = when (requireArguments().getString("ACTION")) {
        Actions.ACTION_ADD.name -> Actions.ACTION_ADD
        Actions.ACTION_UPDATE.name -> Actions.ACTION_UPDATE
        else -> Actions.NONE

    }

    private fun getTitle() = when (requireArguments().getString("ACTION")) {
        Actions.ACTION_ADD.name -> "Add Person"
        Actions.ACTION_UPDATE.name -> "Update Person"
        else -> "Person"
    }
}
