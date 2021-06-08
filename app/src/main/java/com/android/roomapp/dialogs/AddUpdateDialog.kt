package com.android.roomapp.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.android.roomapp.databinding.PersonDialogBinding
import com.android.roomapp.helpers.Actions
import com.android.roomapp.ui.list_filter.ListFilterFragment

interface AddUpdatePersonListener {
    //responsible for adding/updating entry in the PersonDatabase.
    fun addUpdatePerson(actions: Actions, name: String, int: Int)
}

/**
A dialog instantiated from [ListFilterFragment] containing an EditText field, which lets us
1. add a new name to our PersonDatabase.
2. update an existing name in our PersonDatabase.
3. invokes [AddUpdatePersonListener] callback implemented by [ListFilterFragment]
 */
class AddUpdateDialog : DialogFragment() {

    private lateinit var addUpdatePersonListener: AddUpdatePersonListener


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(context)
        val binding = PersonDialogBinding.inflate(layoutInflater)
        addUpdatePersonListener = targetFragment as ListFilterFragment
        dialogBuilder.setView(binding.root)
            .setTitle(getTitle())
            .setNegativeButton("cancel") { _, _ -> }
            .setPositiveButton(
                "ok"
            ) { _, _ ->
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
