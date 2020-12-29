package com.abdulwahabfaiz.roomapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abdulwahabfaiz.roomapp.database.PersonEntity
import com.abdulwahabfaiz.roomapp.databinding.PersonItemBinding
import com.abdulwahabfaiz.roomapp.helpers.Actions

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
    private val onItemClickListener: OnItemClickListener,
    private val layoutInflater: LayoutInflater
) : ListAdapter<PersonEntity, PersonAdapter.PersonViewHolder>(DIFF_CALLBACK), Filterable {

    val fullList: ArrayList<PersonEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder.getInstance(onItemClickListener, layoutInflater, parent)
    }


    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.setPerson(getItem(position))
    }

    class PersonViewHolder private constructor(
        onItemClickListener: OnItemClickListener,
        private val binding: PersonItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var person: PersonEntity

        companion object {
            fun getInstance(
                onItemClickListener: OnItemClickListener,
                layoutInflater: LayoutInflater,
                viewGroup: ViewGroup
            ): PersonViewHolder {
                val binding = PersonItemBinding.inflate(layoutInflater, viewGroup, false)
                return PersonViewHolder(onItemClickListener, binding)
            }
        }

        init {
            binding.btnRemove.setOnClickListener {
                onItemClickListener.onItemClick(Actions.ACTION_REMOVE, person)
            }
            binding.btnEdit.setOnClickListener {
                onItemClickListener.onItemClick(Actions.ACTION_UPDATE, person)
            }
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
        private lateinit var filteredList: List<PersonEntity>

        override fun performFiltering(name: CharSequence?): FilterResults {
            filteredList = if (name.isNullOrEmpty()) {
                fullList
            } else {
                val filterQuery = name.toString().toLowerCase().trim()

                fullList.filter {
                    it.name.contains(filterQuery)
                }
            }

            val results = FilterResults()
            results.values = filteredList

            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?){
            submitList(results!!.values as MutableList<PersonEntity>)
        }

    }
}

