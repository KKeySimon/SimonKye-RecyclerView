package com.example.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.databinding.ListItemCrimeBinding
import com.example.criminalintent.databinding.ListItemSeriousCrimeBinding


// Heavy Inspiration taken from https://blog.devgenius.io/recyclerview-with-multiple-views-in-kotlin-bffe299c1994
class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class SeriousCrimeHolder(
    private val binding: ListItemSeriousCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "A SERIOUS CRIME: ${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

enum class CrimeType(val value: Int) {
    CRIME(1), SERIOUS_CRIME(2)
}

class CrimeListAdapter(private val crimes: List<Crime>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var bindingCrimes: ListItemCrimeBinding
    private lateinit var bindingSeriousCrimes: ListItemSeriousCrimeBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CrimeType.CRIME.value -> {
                bindingCrimes = ListItemCrimeBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                CrimeHolder(bindingCrimes)
            }
            CrimeType.SERIOUS_CRIME.value -> {
                bindingSeriousCrimes = ListItemSeriousCrimeBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                SeriousCrimeHolder(bindingSeriousCrimes)
            }
            else -> {
                bindingCrimes = ListItemCrimeBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                CrimeHolder(bindingCrimes)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentCrime = crimes[position]
        when (holder) {
            is CrimeHolder -> {
                holder.bind(currentCrime)
            }
            is SeriousCrimeHolder -> {
                holder.bind(currentCrime)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice) {
            CrimeType.SERIOUS_CRIME.value
        } else {
            CrimeType.CRIME.value
        }
    }


    override fun getItemCount(): Int {
        return crimes.size
    }

}