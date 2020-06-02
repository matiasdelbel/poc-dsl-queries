package com.delbel.poc.dsl.view

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

class PeopleAdapter : ListAdapter<Person, PersonViewHolder>(PersonDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder.createFrom(parent)

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) =
        holder.bind(person = getItem(position))

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

    fun bind(person: Person) {
        val context = itemView.context

        binding.personAvatar.setImageDrawable(ContextCompat.getDrawable(context, person.avatar))
        binding.personName.text = person.name
        binding.personAge.text = context.getString(R.string.person_age, person.age)
        binding.personRole.text = person.role.capitalize()
        binding.personIsAllow.isChecked = person.isAllowToEnter
    }
}