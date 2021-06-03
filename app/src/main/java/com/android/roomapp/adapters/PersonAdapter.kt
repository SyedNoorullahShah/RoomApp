package com.android.roomapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.roomapp.database.PersonEntity
import com.android.roomapp.databinding.PersonItemBinding
import com.android.roomapp.helpers.Actions
import java.util.*

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PersonEntity>() {
    override fun areItemsTheSame(oldItem: PersonEntity, newItem: PersonEntity) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PersonEntity, newItem: PersonEntity) =
        oldItem == newItem
}

interface OnItemClickListener {
    fun onItemClick(action: Actions, person: PersonEntity)
}


class PersonAdapter(
    private val onItemClickListener: OnItemClickListener?,
    private val layoutInflater: LayoutInflater
) : ListAdapter<PersonEntity, PersonAdapter.PersonViewHolder>(DIFF_CALLBACK), Filterable {

    lateinit var fullList: List<PersonEntity>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder.getInstance(onItemClickListener, layoutInflater, parent)
    }


    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.setPerson(getItem(position))
    }

    class PersonViewHolder private constructor(
        onItemClickListener: OnItemClickListener?,
        private val binding: PersonItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var person: PersonEntity

        companion object {
            fun getInstance(
                onItemClickListener: OnItemClickListener?,
                layoutInflater: LayoutInflater,
                viewGroup: ViewGroup
            ): PersonViewHolder {
                val binding = PersonItemBinding.inflate(layoutInflater, viewGroup, false)
                return PersonViewHolder(onItemClickListener, binding)
            }
        }

        init {
            if (onItemClickListener != null) {
                binding.btnRemove.setOnClickListener {
                    onItemClickListener.onItemClick(Actions.ACTION_REMOVE, person)
                }
                binding.btnEdit.setOnClickListener {
                    onItemClickListener.onItemClick(Actions.ACTION_UPDATE, person)
                }
            } else {
                hideButtons()
            }
        }

        private fun hideButtons() {
            binding.btnEdit.visibility = View.GONE
            binding.btnRemove.visibility = View.GONE
        }

        fun setPerson(person: PersonEntity) {
            this.person = person
            binding.namePerson.text = person.name
        }
    }

    override fun getFilter(): Filter {
        return filter
    }

    private val filter = object : Filter() {
        private var filteredList: List<PersonEntity>? = null

        override fun performFiltering(name: CharSequence?): FilterResults {
            filteredList = getFilteredList(name)

            val results = FilterResults()
            results.values = filteredList

            return results
        }

        private fun getFilteredList(name: CharSequence?) =
            if (name.isNullOrEmpty()) {
                fullList
            } else {
                val filterQuery = name.toString().toLowerCase(Locale.getDefault()).trim()

                fullList.filter {
                    it.name.contains(filterQuery)
                }
            }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            if (results.values !== filteredList) results.values = filteredList
            submitList(results.values as List<PersonEntity>)
        }

    }
}

