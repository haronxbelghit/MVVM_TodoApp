package com.eks.mvvm_course.model

import kotlinx.coroutines.flow.Flow

/*
    Job of the Repo is to access all data sources & decide which data to forward to the ViewModel
    Making this an Interface is a good practice cuz it helps in testing the app with a fake database
    & in case of errors with wont need to worry about the interface, only about the implementation

    Contains same functions as our DAO
 */


interface TodoRepository {


    suspend fun createTodo(todo: Todo)


    suspend fun readTodo_byId(id: Int): Todo? // returns a nullable "To do" cuz the id might not exist


    suspend fun readTodos(): Flow<List<Todo>> // returns all "To do"s || Flow means we get real time updates


    suspend fun updateTodo(todo: Todo)


    suspend fun deleteTodo(todo: Todo)

}