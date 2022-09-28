package com.eks.mvvm_course.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
    This data class represents what data do we want in our room database.
    The @PrimaryKey decorator specifies the, you guessed it, THE PRIMARY KEY
 */
@Entity
data class Todo(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    @PrimaryKey val id: Int? = null
)
