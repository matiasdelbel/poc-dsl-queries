package com.delbel.poc.dsl.view.people

import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.delbel.poc.dsl.R
import com.delbel.poc.dsl.databinding.ItemPersonBinding
import com.delbel.poc.dsl.model.Person

class PeopleAdapter(
    private val listener: OnPersonSelected
) : ListAdapter<Person, PersonViewHolder>(PersonDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder.createFrom(parent)

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) =
        holder.bind(person = getItem(position), listener = listener)

    private object PersonDiffCallback : DiffUtil.ItemCallback<Person>() {

        override fun areItemsTheSame(oldItem: Person, newItem: Person) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Person, newItem: Person) = oldItem == newItem
    }
}

class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {

        fun createFrom(parent: ViewGroup): PersonViewHolder {
            val view = ItemPersonBinding.inflate(from(parent.context), parent, false)
            return PersonViewHolder(view.root)
        }
    }

    private val binding = ItemPersonBinding.bind(itemView)

    fun bind(person: Person, listener: OnPersonSelected) {
        val context = itemView.context

        itemView.setOnClickListener { listener.onPersonSelected(person) }
        binding.personAvatar.setImageDrawable(ContextCompat.getDrawable(context, person.avatar))

        binding.personName.text = person.name
        binding.personAge.text = context.getString(R.string.person_age, person.age)
        binding.personRole.text = person.role.capitalize()

        binding.personIsAllow.setOnCheckedChangeListener(null)
        binding.personIsAllow.isChecked = person.isAllowToEnter
        binding.personIsAllow.setOnCheckedChangeListener { _, isChecked -> listener.onPersonIsAllowChanged(person, isAllow = isChecked) }
    }
}

interface OnPersonSelected {

    fun onPersonSelected(person: Person)

    fun onPersonIsAllowChanged(person: Person, isAllow: Boolean)
}