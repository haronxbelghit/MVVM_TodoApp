package com.eks.mvvm_course.model

import androidx.room.Database
import androidx.room.RoomDatabase

/*
    This is the DB instance where we call the DAO

 */

@Database(
    entities = [Todo::class], // we specify the table
    version = 1 // in prod we shd change this to notify Room to migrate the data
)
abstract class TodoDatabase : RoomDatabase() {

    abstract val dao: TodoDAO
}