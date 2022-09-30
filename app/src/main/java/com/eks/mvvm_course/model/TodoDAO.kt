package com.eks.mvvm_course.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/*
    This interface represents how do we wan tto access the data from our room database.
    We want the functions in our case to be regular CRUD functions.
    Room will generate the implementations for us
 */

@Dao
interface TodoDAO {

    /*
        The suspend is from our lovely coroutines, it basically means that whatever function "createTodo()"
         is called from, suspends its execution until "createTodo()" finishes
    */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTodo(todo: Todo)

    @Query("SELECT * FROM todo WHERE id = :id") // Custom SQL query to get TODOS by id, the column is to specify the arg
    suspend fun readTodo_byId(id: Int): Todo? // returns a nullable "To do" cuz the id might not exist

    @Query("SELECT * FROM todo")
    fun readTodos(): Flow<List<Todo>> // returns all "To do"s || Flow means we get real time updates

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

}