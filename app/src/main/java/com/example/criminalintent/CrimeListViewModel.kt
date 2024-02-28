package com.example.criminalintent

import androidx.lifecycle.ViewModel
import java.util.Date
import java.util.UUID

class CrimeListViewModel : ViewModel() {
    val crimes = mutableListOf<AbstractCrime>()

    init {
        for (i in 0 until 100) {
            val crime : AbstractCrime
            if (i % 2 == 0) {
                crime = Crime(
                    id = UUID.randomUUID(),
                    title = "Crime #$i",
                    date = Date(),
                    isSolved = i % 2 == 0
                )
            } else {
                crime = SeriousCrime(
                    id = UUID.randomUUID(),
                    title = "SERIOUS Crime #$i",
                    numPeopleHarmed = "$i People Harmed!",
                    date = Date(),
                    isSolved = i % 2 == 0
                )
            }
            crimes += crime
        }
    }
}