package com.example.criminalintent

import java.util.Date
import java.util.UUID

data class Crime(
    override val id: UUID,
    override val title: String,
    override val date: Date,
    override val isSolved: Boolean
) : AbstractCrime()

data class SeriousCrime(
    override val id: UUID,
    override val title: String,
    val numPeopleHarmed: String,
    override val date: Date,
    override val isSolved: Boolean
) : AbstractCrime()

abstract class AbstractCrime {
    abstract val id: UUID
    abstract val title: String
    abstract val date: Date
    abstract val isSolved: Boolean
}